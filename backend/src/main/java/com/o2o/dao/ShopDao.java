package com.o2o.dao;

import com.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * @Description: 通过shopId查询店铺
     * @Param: [shopId]
     */
    Shop queryByShopId(long shopId);

    /**
     * @Description: 分页查询商铺
     * @Param: [pageIndex, pageSize]
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * @Description: 根据条件查询符合条件的商铺的数目
     * @Param: [shopContition]
     */
    int queryShopCount(@Param("shopCondition") Shop shopContition);
}
