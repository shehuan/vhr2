package com.sh.vhr.controller;

import com.sh.vhr.model.Hr;
import com.sh.vhr.model.RespBean;
import com.sh.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hr")
public class HrInfoController {
    @Autowired
    HrService hrService;

    @GetMapping("/info")
    public Hr getCurrentHr(Authentication authentication) {
        return ((Hr) authentication.getPrincipal());
    }

    @PutMapping("/update")
    public RespBean updateHr(@RequestBody Hr hr, Authentication authentication) {
        if (hrService.updateHr(hr) == 1) {
            // 重新构建Authentication，放到context
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(hr, authentication.getCredentials(), authentication.getAuthorities()));
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PutMapping("/password")
    public RespBean updatePassword(@RequestBody Map<String, String> info) {
        String oldpass = info.get("oldpass");
        String pass = info.get("pass");
        Integer hrid = Integer.valueOf(info.get("hrid"));
        if (hrService.updatePassword(oldpass, pass, hrid)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
