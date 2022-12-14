package com.shop.ecommerce.dao;

import com.shop.ecommerce.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {


    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

    int insertShopCategory(ShopCategory shopCategory);

    ShopCategory queryShopCategoryById(long shopCategoryId);

    int updateShopCategory(ShopCategory shopCategory);
}
