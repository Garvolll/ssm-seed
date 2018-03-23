package com.o2o.util;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-23
 * Time: 13:37
 */
public class PathUtil {
    private static String separator = System.getProperty("file.separator"); //获取当前系统路径分隔符
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "C:\\java\\workspace\\o2oImg";
        }else {
            basePath = "/home/gjh/image";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }

    public static String getShopImgPath(long shopId){
        String imgPath = "/upload/item/shop/"+shopId+"/";
        return imgPath.replace("/",separator);
    }
}
