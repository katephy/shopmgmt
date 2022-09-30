package com.shop.ecommerce.controller;


import com.shop.ecommerce.entity.ShopCategory;
import com.shop.ecommerce.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class ShopCategoryController {

    @Autowired
    ShopCategoryService shopCategoryService;

    @RequestMapping(value = "/shopcategory", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopCategorys() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> list = new ArrayList<>();
        try {
            //find root
            list = shopCategoryService.getShopCategoryList(null);
            //find child
            list.addAll(shopCategoryService.getShopCategoryList(new ShopCategory()));
            modelMap.put("shopCategoryList", list);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    @RequestMapping(value = "/rootshopcategory", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> list1stLevelShopCategories() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> list = new ArrayList<ShopCategory>();
        try {
            list = shopCategoryService.getShopCategoryList(null);
            modelMap.put("rootShopCategoryList", list);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    @RequestMapping(value = "/childshopcategory", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> list2ndLevelShopCategories() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> list = new ArrayList<ShopCategory>();
        try {
            list = shopCategoryService.getShopCategoryList(new ShopCategory());
            modelMap.put("childShopCategoryList", list);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }


}
