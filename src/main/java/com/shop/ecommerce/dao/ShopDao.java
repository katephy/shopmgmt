package com.shop.ecommerce.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.shop.ecommerce.entity.Shop;

public interface ShopDao {

    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    Shop queryByShopId(long shopId);

    int insertShop(Shop shop);

    int updateShop(Shop shop);
}
