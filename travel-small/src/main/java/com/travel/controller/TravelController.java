package com.travel.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.travel.pojo.Travel;
import com.travel.pojo.ex.TravelEx;
import com.travel.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-03
 */
@Controller
@RequestMapping("travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @RequestMapping("create")
    @ResponseBody
    public Object create(@RequestBody TravelEx travelEx){
        travelService.create(travelEx);
        return  travelEx;
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(@RequestBody JSONObject jsonObject){
        String orderName = jsonObject.getString("orderName");
        int pageNum = jsonObject.getInteger("pageNum");
        int pageSize = jsonObject.getInteger("pageSize");
        Page page = new Page(pageNum, pageSize);
        TravelEx travelEx = new TravelEx();
        travelEx.setOrderName(orderName);
        return travelService.list(travelEx, page);
    }

    @RequestMapping("detail")
    @ResponseBody
    public Object detail(String id){
        return travelService.detail(id);
    }
}
