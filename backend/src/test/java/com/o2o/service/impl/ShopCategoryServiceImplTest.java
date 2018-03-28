package com.o2o.service.impl;

import com.o2o.BaseTest;
import com.o2o.dto.ShopCategoryExecution;
import com.o2o.entity.ShopCategory;
import com.o2o.service.ShopCategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-28
 * Time: 13:49
 */
public class ShopCategoryServiceImplTest  extends BaseTest{
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void getShopCategoryList() throws Exception {

    }

    @Test
    public void getShopCategoryById() throws Exception {
        super.logger.info("res={}",shopCategoryService.getShopCategoryById(1L).getShopCategory());
    }

    @Test
    public void insertShopCategory() throws Exception {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryName("test");
        shopCategory.setPriority(10);
        shopCategory.setShopCategoryDesc("test");
        File shopCategoryImg = new File("C:\\Users\\sys-12\\Pictures\\IMG_0169.jpg");
        InputStream is = new FileInputStream(shopCategoryImg);
        ShopCategoryExecution shopCategoryExecution = shopCategoryService.insertShopCategory(shopCategory,is,"nba.jpg");
        super.logger.info("res={}",shopCategoryExecution);
    }

    @Test
    public void deleteShopCategory() throws Exception {
        boolean res= shopCategoryService.deleteShopCategory(12L);
    }

    @Test
    public void updateShopCategory() throws Exception {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(14L);
        shopCategory.setShopCategoryName("test1");
        shopCategory.setPriority(12);
        shopCategory.setShopCategoryDesc("test1");
        File shopCategoryImg = new File("C:\\Users\\sys-12\\Pictures\\IMG_0169.jpg");
        InputStream is = new FileInputStream(shopCategoryImg);
        ShopCategoryExecution shopCategoryExecution = shopCategoryService.updateShopCategory(shopCategory,is,"nba.jpg");
        super.logger.info("res={}",shopCategoryExecution);
    }

}