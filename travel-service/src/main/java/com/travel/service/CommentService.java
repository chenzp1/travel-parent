package com.travel.service;

import com.travel.common.StringUtil;
import com.travel.dao.CommentMapper;
import com.travel.dao.FileMapper;
import com.travel.pojo.File;
import com.travel.pojo.ex.CommentEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-09
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private FileMapper fileMapper;

    public Object create(CommentEx commentEx){
        String id = StringUtil.getUuid();
        commentEx.setId(id);
        commentEx.setCreateTime(new Date());
        commentMapper.insertSelective(commentEx);
        List<File> files = commentEx.getFiles();
        files.forEach(file -> {
            file.setRelationId(id);
            file.setRelationType("comment");
            fileMapper.updateByPrimaryKeySelective(file);
        });
        return commentEx;
    }
}
