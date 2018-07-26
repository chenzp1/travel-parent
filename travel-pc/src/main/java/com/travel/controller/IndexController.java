package com.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-07-26
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
