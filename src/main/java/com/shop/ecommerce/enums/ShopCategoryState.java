package com.shop.ecommerce.enums;

public enum  ShopCategoryState {
    SUCCESS(0, "this shop category is created successfully"),
    INTERNAL_ERROR(-1001, "internal error"),
    EMPTY(-1002, "empty info");

    private int state;
    private String stateInfo;

    private ShopCategoryState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ShopCategoryState stateOf(int index) {
        for (ShopCategoryState state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }


}
