package com.travel.controller;

import com.travel.pojo.Travel;
import com.travel.pojo.ex.TravelEx;
import com.travel.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
    public Object create(TravelEx travelEx){
        travelService.create(travelEx);
        return  travelEx;
    }
}
