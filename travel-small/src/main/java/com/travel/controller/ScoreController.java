package com.travel.controller;

import com.travel.pojo.Score;
import com.travel.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-17
 */
@Controller
@RequestMapping("score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("create")
    @ResponseBody
    public Object create(@RequestBody Score score){
        return scoreService.create(score);
    }
}
