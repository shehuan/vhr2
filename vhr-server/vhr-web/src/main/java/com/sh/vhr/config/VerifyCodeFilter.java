package com.sh.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.vhr.model.RespBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class VerifyCodeFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if ("POST".equals(req.getMethod()) && "/doLogin".equals(req.getServletPath())) {
            String code = req.getParameter("code");
            String verify_code = (String) req.getSession().getAttribute("verify_code");
            if (StringUtils.isEmpty(code) || StringUtils.isEmpty(verify_code) || !code.equals(verify_code)) {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(RespBean.error("验证码错误！")));
                out.flush();
                out.close();
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
