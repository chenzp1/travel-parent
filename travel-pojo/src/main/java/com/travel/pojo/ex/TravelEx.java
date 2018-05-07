package com.travel.pojo.ex;

import com.travel.pojo.Comment;
import com.travel.pojo.File;
import com.travel.pojo.Travel;

import java.util.List;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-03
 */
public class TravelEx extends Travel {

    private String orderName;

    private List<File> files;

    private List<CommentEx> commentExes;

    public List<CommentEx> getCommentExes() {
        return commentExes;
    }

    public void setCommentExes(List<CommentEx> commentExes) {
        this.commentExes = commentExes;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
