package com.shop.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShopCategory {
    /**
     *     A: root ~ parentId = null
     *    /
     *   B: child ~ parentId: A != null
     */
    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date timeCreated;
    private Date timeUpdated;
    private Long parentId;
}
