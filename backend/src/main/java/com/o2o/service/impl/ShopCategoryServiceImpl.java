package com.o2o.service.impl;

import com.o2o.dao.ShopCategoryDao;
import com.o2o.entity.ShopCategory;
import com.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-26
 * Time: 10:52
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition){
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
