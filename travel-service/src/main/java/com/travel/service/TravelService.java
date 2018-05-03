package com.travel.service;

import com.travel.common.StringUtil;
import com.travel.dao.FileMapper;
import com.travel.dao.TravelMapper;
import com.travel.pojo.File;
import com.travel.pojo.ex.TravelEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-03
 */
@Service
public class TravelService {

    @Autowired
    private TravelMapper travelMapper;

    @Autowired
    private FileMapper fileMapper;

    @Transactional
    public TravelEx create(TravelEx travelEx){
        String  travelId = StringUtil.getUuid();
        travelEx.setId(travelId);
        travelEx.setCreateTime(new Date());
        travelMapper.insertSelective(travelEx);
        int i = 1/0;
        List<File> files = travelEx.getFiles();
        files.forEach(file -> {
            file.setRelationId(travelId);
            file.setRelationType("travel");
            fileMapper.updateByPrimaryKeySelective(file);
        });
        return  travelEx;
    }
}
