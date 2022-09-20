package com.shop.ecommerce.controller;

import com.shop.ecommerce.entity.Area;
import com.shop.ecommerce.request.area.GetAreaByIdRequest;
import com.shop.ecommerce.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    private String testHello(){
        return "Hello world from spring controller";
    }

    @GetMapping(value = "/listarea")
    @ResponseBody
    private Map<String, Object> listArea() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<>();
        try {
            list = areaService.getAreaList();
            modelMap.put("data", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    //getAreaById
    @GetMapping(value = "/area")
    @ResponseBody
    private Map<String, Object> getAreaById(@RequestParam(value = "id") long areaId) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Area area = areaService.getAreaById(areaId);
            modelMap.put("data", area);
        } catch (BadSqlGrammarException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Bad SQL Query");
        }
        return modelMap;
    }

    @GetMapping(value = "/v3/area/{areaId}")
    @ResponseBody
    private Map<String, Object> getAreaByIdV3(@PathVariable(value = "areaId") long areaId) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Area area = areaService.getAreaById(areaId);
            modelMap.put("data", area);
        } catch (BadSqlGrammarException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Bad SQL Query");
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Additional Exceptions");
        }
        return modelMap;
    }

    @PostMapping(value = "/v2/area", consumes = "application/json")
    @ResponseBody
    public Map<String, Object> getAreaByIdV2(@RequestBody GetAreaByIdRequest getAreaByIdRequest) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Area area = areaService.getAreaById(getAreaByIdRequest.getAreaId());
            modelMap.put("data", area);
        } catch (BadSqlGrammarException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Bad SQL Query");
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Additional Exceptions");
        }
        return modelMap;
    }



}
