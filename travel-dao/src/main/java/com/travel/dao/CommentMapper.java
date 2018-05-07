package com.travel.dao;

import com.travel.pojo.Comment;
import com.travel.pojo.CommentExample;
import com.travel.pojo.ex.CommentEx;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment, CommentExample>{

    List<CommentEx> queryByRelationId(String relationId);
}