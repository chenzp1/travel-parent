package com.travel.service;


import com.travel.common.StringUtil;
import com.travel.dao.FileMapper;
import com.travel.pojo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-03
 */
@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;


    public File create(File file){
        file.setId(StringUtil.getUuid());
        file.setCreateTime(new Date());
        fileMapper.insertSelective(file);
        return file;
    }
}
