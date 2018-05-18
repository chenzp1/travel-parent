package com.travel.service;

import com.travel.common.StringUtil;
import com.travel.dao.ScoreMapper;
import com.travel.pojo.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-17
 */
@Service
public class ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    public Score create(Score score){
        score.setCreateTime(new Date());
        score.setId(StringUtil.getUuid());
        scoreMapper.insertSelective(score);
        return score;
    }
}
