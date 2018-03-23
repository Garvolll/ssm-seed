package com.o2o.service;

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
     * 注册店铺信息，包括图片处理
     * @param shop
     * @return
     */
    ShopExecution addShop(Shop shop,InputStream shopImg,String fileName) ;
}
