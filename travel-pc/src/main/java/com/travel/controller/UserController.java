package com.travel.controller;

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

    @RequestMapping("login")
    @ResponseBody
    public String login(){
        return "user/login";
    }
}
