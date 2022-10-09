package com.shop.ecommerce.utils;

public class PathUtil {

    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "~~~~~~~";
        } else {
            basePath = "/Users/kate/Desktop/imgs/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopCategoryPath() {
        String imagePath = "upload/images/shopCategory/";
        return imagePath.replace("/", seperator);
    }


}
