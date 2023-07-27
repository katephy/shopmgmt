package com.shop.ecommerce.exceptions;

import java.io.Serializable;

public class ShopOperationException extends RuntimeException{
    public ShopOperationException(String msg) {
        super(msg);
    }
}
