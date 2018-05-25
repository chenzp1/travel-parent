package com.travel.dao;

import com.travel.pojo.City;
import com.travel.pojo.CityExample;

import java.util.List;

public interface CityMapper extends BaseMapper<City, CityExample>{

    List<City> queryTravelCity();
}