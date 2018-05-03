package com.travel.controller;

import com.travel.common.FastDFSClient;
import com.travel.pojo.File;
import com.travel.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-02
 */
@Controller
@RequestMapping("file")
public class FileController {


    @Autowired
    private FileService fileService;

    @RequestMapping("upload")
    @ResponseBody
    public Object upload(@RequestParam("file") CommonsMultipartFile file){
        String[] strings = new String[0];
        try {
            strings = FastDFSClient.uploadFile(file.getBytes(), file.getOriginalFilename(),file.getSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
        File file1 = new File();
        file1.setPath(strings[0]+"/"+strings[1]);
        fileService.create(file1);
        System.out.println(Arrays.toString(strings));
        return strings;
    }
}
