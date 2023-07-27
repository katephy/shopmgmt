package com.shop.ecommerce.service.impl;

import com.shop.ecommerce.dao.ShopDao;
import com.shop.ecommerce.dto.ImageHolder;
import com.shop.ecommerce.dto.ShopExecution;
import com.shop.ecommerce.entity.Shop;
import com.shop.ecommerce.enums.ShopState;
import com.shop.ecommerce.exceptions.ShopOperationException;
import com.shop.ecommerce.service.ShopService;
import com.shop.ecommerce.utils.ImageUtil;
import com.shop.ecommerce.utils.PageUtil;
import com.shop.ecommerce.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageUtil.calculateRowIndex(pageIndex, pageSize);

        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);

        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopState.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopState.NULL_SHOP);
        } else {
            try {
                if (thumbnail.getImage() != null && thumbnail.getImageName() != null
                        && !"".equals(thumbnail.getImageName())) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, thumbnail);
                }

                shop.setTimeUpdated(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopState.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopState.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null) {
            return new ShopExecution(ShopState.NULL_SHOP);
        }
        try {

            shop.setEnableStatus(0);
            shop.setTimeCreated(new Date());
            shop.setTimeCreated(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("add shop error");
            } else {
                if (thumbnail.getImage() != null) {
                    try {
                        addShopImg(shop, thumbnail);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("update shop image error");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopState.CHECK, shop);
    }

    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        String dest = PathUtil.getShopPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateNormalImg(thumbnail, dest);
        shop.setShopImg(shopImgAddr);
    }
}
