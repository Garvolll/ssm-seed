package com.o2o.dao;

import com.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.List;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-26
 * Time: 10:34
 */
public interface ShopCategoryDao {
    /**
    * @Description: 查询商品分类信息
    * @Param: [shopCategoryCondition]
    */
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

    /** 
    * @Description: 插入商品信息
    * @Param: [shopCategoryCondition] 
    */ 
    int insertShopCategory(ShopCategory shopCategoryCondition);

    /** 
    * @Description: 根据Id查找商品类别
    * @Param: [shopCategoryID] 
    */ 
    ShopCategory queryShopCategoryById(long shopCategoryID);
    
    /** 
    * @Description: 根据传入的Id列表查询店铺类别信息(供超级管理员选定删除类别的时候用，主要是处理图片) 
    * @Param: [shopCategoryIdList] 
    */ 
    List<ShopCategory> queryShopCategoryByIds(List<Long> shopCategoryIdList);
    
    /** 
    * @Description: 更新商铺类别信息
    * @Param: [shopCategory] 
    */ 
    int updateShopCategory(ShopCategory shopCategory);

    /**
    * @Description: 删除商品类别信息
    * @Param: [shopCategoryId]
    */
    int deleteShopCategory(long shopCategoryId);

    /** 
    * @Description: 批量删除商品类别信息 
    * @Param: [shopCategoryIdList] 
    */ 
    int batchDeleteShopCategory(List<Long> shopCategoryIdList);
}
