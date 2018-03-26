package com.o2o.service;

import com.o2o.entity.ShopCategory;

import java.util.List;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-26
 * Time: 10:49
 */
public interface ShopCategoryService {
    /** 
    * @Description: 根据查询条件获取ShopCategory列表
    * @Param: [shopCategoryCondition] 
    */ 
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
