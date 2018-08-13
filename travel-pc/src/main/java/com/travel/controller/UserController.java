package com.travel.controller;

import com.travel.pojo.User;
import com.travel.service.EmailService;
import com.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-08-08
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Value("${contextpath}")
    private String contextpath;

    @RequestMapping("login")
    @ResponseBody
    public Object login(User user){
        user = userService.login(user);
        Integer status = user.getStatus();
        switch (status){
            case 2:  //未激活 发送邮件
                emailService.sendText(user.getEmail(),"52trip账号激活",contextpath+"user/active?uuid="+user.getId());
                break;
            case 1:
        }

        return "user/login";
    }
}
