package com.shop.ecommerce.service;

import com.shop.ecommerce.dto.AreaExecution;
import com.shop.ecommerce.entity.Area;

import java.util.List;

public interface AreaService {

    List<Area> getAreaList();

    Area getAreaById(long areaId);

    AreaExecution addArea(Area area);

    AreaExecution updateArea(Area area);

    AreaExecution removeArea(long areaId);
}
