package com.bookkeep.controller.baseController;

import com.bookkeep.domain.User;
import com.bookkeep.service.LoginService;
import com.bookkeep.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author scn
 * @create 2018-03-09 10:16
 * @desc 登录Controller
 **/
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/loginIn")
    public ReturnResult loginIn(User user, HttpSession session) {
        try {
            if (user == null || StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getPassword())) {
                return ReturnResult.error("存在空值");
            }
            //校验用户名密码准确性
            User userA = loginService.checkUser(user);
            if (userA == null) {
                return ReturnResult.error("手机号或密码错误");
            }
            session.setAttribute("userId", userA.getId());
            session.setAttribute("loginName", userA.getLoginName());
            session.setAttribute("type", userA.getType());
            return ReturnResult.success("登录成功");
        } catch (Exception e) {
            return ReturnResult.error("登录失败" + e);
        }
    }
}
