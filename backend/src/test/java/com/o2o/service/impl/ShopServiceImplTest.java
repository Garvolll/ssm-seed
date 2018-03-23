package com.o2o.service.impl;

import com.o2o.BaseTest;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Area;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.entity.User;
import com.o2o.enums.ShopStateEnum;
import com.o2o.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-23
 * Time: 14:38
 */
public class ShopServiceImplTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void addShop() throws Exception {
        Shop shop = new Shop();
        User user = new User();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        user.setUserId(15L);
        area.setAreaId(4);
        shopCategory.setShopCategoryId(5L);

        shop.setArea(area);
        shop.setOwner(user);
        shop.setShopCategory(shopCategory);
        shop.setShopName("布尔玛小店");
        shop.setShopDesc("test");
        shop.setAdvice("test");
        shop.setPhone("18599999556");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setPriority(3);
        shop.setShopAddr("ttt");
        File shopImg = new File("C:\\Users\\sys-12\\IdeaProjects\\o2o\\src\\main\\resources\\avatar.jpg");
        InputStream is= new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop,is,shopImg.getName());
        if(se.getState()==ShopStateEnum.CHECK.getState()){
            super.logger.info("res={}",se);
        }
    }

}