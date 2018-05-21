package com.travel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.travel.common.HttpTools;
import com.travel.dao.TravelMapper;
import com.travel.pojo.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-18
 */
@Component
public class TravelServiceTimer {

    @Autowired
    private TravelMapper travelMapper;

    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void getTravelInfo() throws Exception {
        String b = new HttpTools().getString("https://lvyou.baidu.com/destination/ajax/jingdian?format=ajax&cid=0&playid=0&seasonid=5&surl=sichuan&pn=1&rn=18", "");
        JSONObject jsonObject = JSONObject.parseObject(b);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("scene_list");
        for(int i = 0; i < jsonArray.size();i++){
            JSONObject record = jsonArray.getJSONObject(i);
            String travelName = record.getString("ambiguity_sname");  //地名
            String province = record.getString("province");  //地名
            String desc = record.getJSONObject("ext").getString("more_desc");
            Travel travel = new Travel();
            travel.setContent(desc);
            travel.setName(travelName);
            travel.setCreateTime(new Date());
            travelMapper.insertSelective(travel);
        }
    }

    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        return str;
    }

}
