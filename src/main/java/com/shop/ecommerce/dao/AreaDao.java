package com.shop.ecommerce.dao;

import com.shop.ecommerce.entity.Area;

import java.util.List;

public interface AreaDao {

    List<Area> queryAreaList();

    Area getAreaByAreaId(long areaId);
}
