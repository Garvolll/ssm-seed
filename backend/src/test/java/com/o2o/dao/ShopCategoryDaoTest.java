package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-26
 * Time: 10:42
 */
public class ShopCategoryDaoTest extends BaseTest{
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void queryShopCategory() throws Exception {
        List<ShopCategory> list = shopCategoryDao.queryShopCategory(new ShopCategory());
        super.logger.info("list={}",list);
    }

}