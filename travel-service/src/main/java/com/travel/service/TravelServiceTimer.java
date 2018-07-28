package com.travel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.travel.common.HttpTools;
import com.travel.common.StringUtil;
import com.travel.dao.CityMapper;
import com.travel.dao.TravelMapper;
import com.travel.pojo.City;
import com.travel.pojo.CityExample;
import com.travel.pojo.Travel;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
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

    @Autowired
    private CityMapper cityMapper;

    Logger logger = LoggerFactory.getLogger(TravelServiceTimer.class);

    @Scheduled(cron="0 0/5 *  * * ? ")   //每小时执行一次
    public void getTravelInfo(){
        List<City> cities = cityMapper.queryTravelCity();
        cities.forEach(city -> {
                for (int j = 0; j < 10; j++) {
                    String b = null;
                    try {
                        String s = city.getPinyin().replaceAll("shi","").replaceAll("sheng","").replaceAll("zizhiqu","").replaceAll("zhuangzuzizhiqu","")
                                .replaceAll("huizuzizhiqu","").replaceAll("weiwuerzizhiqu","").replaceAll("tebiexingzhengqu","");
                        b = new HttpTools().getString("https://lvyou.baidu.com/destination/ajax/jingdian?format=ajax&cid=0&playid=0&seasonid=5&surl="+s+"&pn="+j+"&rn=18", "");
                        logger.info(b);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    JSONObject jsonObject = JSONObject.parseObject(b);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = jsonObject.getJSONObject("data").getJSONArray("scene_list");
                    } catch (Exception e) {
                      break;
                    }
                    for(int i = 0; i < jsonArray.size();i++){
                        JSONObject record = jsonArray.getJSONObject(i);
                        String travelName = record.getString("ambiguity_sname");  //地名
                        String desc = record.getJSONObject("ext").getString("more_desc");
                        Travel travel = new Travel();
                        travel.setId(StringUtil.getUuid());
                        travel.setContent(desc);
                        travel.setName(travelName);
                        travel.setCreateTime(new Date());
                        travel.setProvince(city.getName());
                        travelMapper.insertSelective(travel);
                    }
                }
        });
    }


    @Scheduled(cron="0 0/1 *  * * ? ")   //每5分钟执行一次
    public void translatePY(){
        CityExample cityExample = new CityExample();
        cityExample.createCriteria().andPinyinIsNull();
        List<City> cities = cityMapper.selectByExample(cityExample);
        cities.parallelStream().forEach(n ->{
            String pinyin = "";
            try {
                pinyin = PinyinHelper.toHanYuPinyinString(n.getName(), new HanyuPinyinOutputFormat(), "", true);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            pinyin = pinyin.replaceAll("\\d*","");
            n.setPinyin(pinyin);
            cityMapper.updateByPrimaryKey(n);
        });
    }



    public static String unicodeToString(String str) {
        java.util.Arrays.sort(new int[]{2,6,8,7});
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        return str;
    }

    public static void main(String[] args) {

            for (int i = 0; i < 10; i++) {
                System.out.println(1111);
                try {
                int j = 1/0;
                }catch (Exception e){
                    e.printStackTrace();
                }
                long s = 13128802535l;
                System.out.println(s%50);
            }


    }
}
