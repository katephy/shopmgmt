package com.shop.ecommerce.service;

import com.shop.ecommerce.entity.Area;

import java.util.List;

public interface AreaService {

    List<Area> getAreaList();

    Area getAreaById(long areaId);
}
