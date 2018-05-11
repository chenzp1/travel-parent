package com.travel.controller;

import com.travel.pojo.ex.CommentEx;
import com.travel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-09
 */
@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("create")
    @ResponseBody
    public Object create(@RequestBody CommentEx commentEx){
        commentEx.setRelationType("comment");
        return  commentService.create(commentEx);
    }
}
