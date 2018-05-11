package com.travel.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.StringUtil;
import com.travel.dao.CommentMapper;
import com.travel.dao.FileMapper;
import com.travel.dao.ScoreMapper;
import com.travel.dao.TravelMapper;
import com.travel.pojo.File;
import com.travel.pojo.Score;
import com.travel.pojo.ScoreExample;
import com.travel.pojo.ex.CommentEx;
import com.travel.pojo.ex.TravelEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-03
 */
@Service
@Transactional
public class TravelService {

    @Autowired
    private TravelMapper travelMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    public TravelEx create(TravelEx travelEx){
        String  travelId = StringUtil.getUuid();
        travelEx.setId(travelId);
        travelEx.setCreateTime(new Date());
        travelMapper.insertSelective(travelEx);
        List<File> files = travelEx.getFiles();
        files.forEach(file -> {
            file.setRelationId(travelId);
            file.setRelationType("travel");
            fileMapper.updateByPrimaryKeySelective(file);
        });
        return  travelEx;
    }

    public Map list(TravelEx travelEx, Page page){
        Map map = new HashMap();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<TravelEx> travelExes = travelMapper.list(travelEx);
        PageInfo<TravelEx> pageInfo = new PageInfo<>(travelExes);
        map.put("total", pageInfo.getPages());
        map.put("list", travelExes);
        return map;
    }

    public TravelEx detail(String id) {
        TravelEx travelEx = travelMapper.detail(id);
        List<CommentEx> commentExes = commentMapper.queryByRelationId(id);
        travelEx.setCommentExes(commentExes);
        ScoreExample scoreExample = new ScoreExample();
        scoreExample.createCriteria().andRelationIdEqualTo(id);
        List<Score> scores = scoreMapper.selectByExample(scoreExample);
        travelEx.setScores(scores);
        return travelEx;
    }
}
