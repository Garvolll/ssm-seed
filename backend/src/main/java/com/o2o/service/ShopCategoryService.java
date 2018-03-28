package com.o2o.service;

import com.o2o.dto.ShopCategoryExecution;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;

import java.io.InputStream;
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

    /**
    * @Description: 根据Id查询商品分类信息
    * @Param: [shopCategoryId]
    */
    ShopCategoryExecution getShopCategoryById(long shopCategoryId);

    /** 
    * @Description: 插入新的商品类别信息 
    * @Param: [shopCategory] 
    */ 
    ShopCategoryExecution insertShopCategory(ShopCategory shopCategory, InputStream shopCategoryImg, String fileName);
    
    /** 
    * @Description: 删除商品类别信息 
    * @Param: [shopCategoryId] 
    */ 
    boolean deleteShopCategory(long shopCategoryId);
    
    /**
    * @Description: 更新商品类别信息 
    * @Param: [shopCategory] 
    */ 
    ShopCategoryExecution updateShopCategory(ShopCategory shopCategory, InputStream shopCategoryImg, String fileName);
}
