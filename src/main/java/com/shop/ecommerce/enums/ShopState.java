package com.shop.ecommerce.enums;

public enum ShopState {
    CHECK(0, "processing"),
    OFFLINE(-1, "illegal"),
    SUCCESS(1, "success"),
    PASS(2, "confirmed"),
    INNER_ERROR(-1001, "inner error"),
    NULL_SHOP_ID(-1002, "empty shopId"),
    NULL_SHOP(-1003, "empty shop");
    private int state;
    private String stateInfo;

    private ShopState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopState stateOf(int state) {
        for (ShopState stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
