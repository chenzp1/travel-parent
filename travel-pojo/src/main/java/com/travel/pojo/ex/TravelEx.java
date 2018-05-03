package com.travel.pojo.ex;

import com.travel.pojo.File;
import com.travel.pojo.Travel;

import java.util.List;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-03
 */
public class TravelEx extends Travel {

    private List<File> files;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
