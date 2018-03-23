package com.o2o.dao;

import com.o2o.entity.Shop;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-22
 * Time: 15:58
 */
public interface ShopDao {
    /** 
    * @Description: 新增店铺 
    * @Param: [shop] 
    */ 
    int insertShop(Shop shop);

    /** 
    * @Description: 更新商铺信息
    * @Param: [shop] 
    */ 
    int updateShop(Shop shop);
}
