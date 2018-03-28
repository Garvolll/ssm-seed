package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import com.o2o.entity.Shop;
import com.o2o.entity.ShopCategory;
import com.o2o.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-22
 * Time: 16:13
 */
public class ShopDaoTest extends BaseTest{
    @Autowired
    private ShopDao shopDao;
    @Test
    public void insertShop() throws Exception {
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
        shop.setShopName("布尔玛礼品店");
        shop.setShopDesc("test");
        shop.setAdvice("test");
        shop.setPhone("18599999556");
        shop.setEnableStatus(1);
        shop.setPriority(3);
        shop.setShopAddr("sss");
        shop.setShopImg("");
        int effectNum = shopDao.insertShop(shop);
    }

    @Test
    public void updateShop() throws Exception {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("少年jump50周年纪念书店");
        shop.setAdvice("多来点");
        int effectNum =  shopDao.updateShop(shop);
    }

    @Test
    public void queryByShopId() throws Exception {
       super.logger.info("res={}",shopDao.queryByShopId(58L));
    }
}