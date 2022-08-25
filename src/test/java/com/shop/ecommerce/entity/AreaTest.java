package com.shop.ecommerce.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AreaTest {

    @Test
    public void testArea() {
        Area area = new Area();
        Long areaId = 12345L;
        area.setAreaId(areaId);
        String areaName = "AreaName";
        area.setAreaName(areaName);
        assertEquals(areaId, area.getAreaId());
        assertEquals(areaName, area.getAreaName());
    }

}
