package com.travel.service;

import com.alibaba.fastjson.JSONObject;
import com.travel.common.HttpTools;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-18
 */
@Component
public class TravelServiceTimer {

    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void getTravelInfo(){
        System.out.println("进入测试");
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


    public static void main(String[] args) throws Exception {
        String b = new HttpTools().getString("https://lvyou.baidu.com/destination/ajax/jingdian?format=ajax&cid=0&playid=0&seasonid=5&surl=sichuan&pn=100&rn=18", "");
        JSONObject jsonObject = JSONObject.parseObject(b);
        String desc = jsonObject.getJSONObject("data").getJSONArray("scene_list").getJSONObject(0).getJSONObject("ext").getString("more_desc");
        System.out.println(desc);
    }
}
