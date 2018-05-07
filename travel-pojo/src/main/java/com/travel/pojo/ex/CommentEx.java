package com.travel.pojo.ex;

import com.travel.pojo.Comment;
import com.travel.pojo.File;

import java.util.List;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-07
 */
public class CommentEx  extends Comment {

    private List<File> files;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
