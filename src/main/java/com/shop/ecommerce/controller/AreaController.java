package com.shop.ecommerce.controller;

import com.shop.ecommerce.dto.AreaExecution;
import com.shop.ecommerce.entity.Area;
import com.shop.ecommerce.enums.AreaState;
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

    @RequestMapping(value = "/addarea", method = RequestMethod.POST) //Endpoint ~ URL
    @ResponseBody
    private Map<String, Object> addArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<>();
        // valid request -> area
        if (area != null && area.getAreaName() != null && !area.getAreaName().isEmpty()) {
            // add area operations
            AreaExecution areaExecution = areaService.addArea(area);
            if (areaExecution.getState() == AreaState.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else{
                modelMap.put("success", false);
                modelMap.put("errMsg", areaExecution.getStateInfo());
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Please enter the area info");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyarea", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> updateArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<>();
        // valid request -> area
        if (area != null && area.getAreaId() != null) {
            // update area operations
            AreaExecution areaExecution = areaService.updateArea(area);
            if (areaExecution.getState() == AreaState.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else{
                modelMap.put("success", false);
                modelMap.put("errMsg", areaExecution.getStateInfo());
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Please enter the area info");
        }
        return modelMap;
    }

    @RequestMapping(value = "/delete/area/{id}", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyArea(@PathVariable Long id) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            AreaExecution ae = areaService.removeArea(id);
            if (ae.getState() == AreaState.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", ae.getStateInfo());
            }
        } catch (RuntimeException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }

        return modelMap;
    }





}
