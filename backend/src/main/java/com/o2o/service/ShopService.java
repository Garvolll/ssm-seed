package com.o2o.service;

import com.o2o.Exception.ShopOperationException;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-23
 * Time: 11:29
 */
public interface ShopService {
    /**
    * @Description: Shop shopCondition, int pageIndex, int pageSize
    * @Param: [shopCondition, pageIndex, pageSize] 
    */ 
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
    /**
     * 注册店铺信息，包括图片处理
     * @param shop
     * @return
     */
    ShopExecution addShop(Shop shop,InputStream shopImg,String fileName) throws ShopOperationException;

    /** 
    * @Description: 根据id查找shop 
    * @Param: [shopId] 
    */ 
    Shop getByShopId(long shopId) ;

    /** 
    * @Description: 更新店铺信息
    */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException;

}
