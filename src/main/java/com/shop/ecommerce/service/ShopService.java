package com.shop.ecommerce.service;

import com.shop.ecommerce.dto.ImageHolder;
import com.shop.ecommerce.dto.ShopExecution;
import com.shop.ecommerce.entity.Shop;
import com.shop.ecommerce.exceptions.ShopOperationException;

public interface ShopService {

    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    Shop getByShopId(long shopId);


    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;


    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
