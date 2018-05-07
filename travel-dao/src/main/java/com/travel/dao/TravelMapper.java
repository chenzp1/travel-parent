package com.travel.dao;

import com.travel.pojo.Travel;
import com.travel.pojo.TravelExample;
import com.travel.pojo.ex.TravelEx;

import java.util.List;

public interface TravelMapper extends BaseMapper<Travel, TravelExample>{
    List<TravelEx> list(TravelEx travelEx);

    TravelEx detail(String id);
}