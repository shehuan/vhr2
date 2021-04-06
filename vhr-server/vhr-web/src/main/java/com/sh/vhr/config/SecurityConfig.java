package com.sh.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.vhr.model.Hr;
import com.sh.vhr.model.RespBean;
import com.sh.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;

    @Autowired
    MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Autowired
    MyAccessDecisionManager myAccessDecisionManager;

    @Autowired
    VerifyCodeFilter verifyCodeFilter;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService).passwordEncoder(passwordEncoder());
    }

    /**
     * 在这里可以配置那些不需要登录就可以访问的接口
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/verifyCode");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 在用户名密码过滤器前添加验证码过滤器
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                // 配置动态权限
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(myAccessDecisionManager);
                        object.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/login")
                // 处理登录逻辑的接口
                .loginProcessingUrl("/doLogin")
                // 登录成功
                .successHandler((request, response, authentication) -> {
                    Hr hr = (Hr) authentication.getPrincipal();
                    hr.setPassword(null);
                    RespBean respBean = RespBean.ok("登录成功", hr);
                    String s = new ObjectMapper().writeValueAsString(respBean);
                    writeMessage(response, s);
                })
                // 登录失败
                .failureHandler((request, response, e) -> {
                    RespBean respBean = RespBean.error("登录失败，请稍后再试！");
                    if (e instanceof BadCredentialsException) {
                        respBean.setMsg("用户名或者密码错误，请重新输入！");
                    } else if (e instanceof DisabledException) {
                        respBean.setMsg("账号被禁用，请联系管理员！");
                    } else if (e instanceof LockedException) {
                        respBean.setMsg("账号被锁，请联系管理员！");
                    } else if (e instanceof AccountExpiredException) {
                        respBean.setMsg("账号过期，请联系管理员！");
                    } else if (e instanceof CredentialsExpiredException) {
                        respBean.setMsg("密码过期，请联系管理员！");
                    }
                    writeMessage(response, new ObjectMapper().writeValueAsString(respBean));
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    String s = new ObjectMapper().writeValueAsString(RespBean.ok("退出登录成功！"));
                    writeMessage(response, s);
                })
                .permitAll()
                .and()
                .logout()
                // 设置退出登录的请求地址，GET请求，默认就是/logout，可以自定义一个GET请求的接口
                .logoutUrl("/logout")
                // 退出登录后的回调
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    String s = new ObjectMapper().writeValueAsString(RespBean.ok("已注销登录！"));
                    writeMessage(httpServletResponse, s);
                })
                .permitAll()
                .and()
                .exceptionHandling()
                // 访问接口时，如果未登录则给出提示，而不是跳转到登录页面
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                    // session正常过期、后端重启，导致前端session不可用，设置http401，让前端处理
                    httpServletResponse.setStatus(401);
                    RespBean respBean = RespBean.error("访问失败！");
                    if (e instanceof InsufficientAuthenticationException) {
                        respBean.setMsg("尚未登录，请先登录！");
                    }
                    String s = new ObjectMapper().writeValueAsString(respBean);
                    writeMessage(httpServletResponse, s);
                })
                .and()
                .csrf().disable();
    }

    private void writeMessage(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(message);
        out.flush();
        out.close();
    }
}
