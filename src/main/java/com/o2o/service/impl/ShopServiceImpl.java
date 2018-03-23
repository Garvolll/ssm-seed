package com.o2o.service.impl;

import com.o2o.Exception.ShopOperationException;
import com.o2o.dao.ShopDao;
import com.o2o.dto.ShopExecution;
import com.o2o.entity.Shop;
import com.o2o.enums.ShopStateEnum;
import com.o2o.service.ShopService;
import com.o2o.util.ImageUtil;
import com.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-23
 * Time: 11:31
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            shop.setEnableStatus(0);
            shop.setLastEditTime(new Date());
            int effectNum = shopDao.insertShop(shop);
            if (effectNum <= 0) {
                throw new ShopOperationException("店铺创建失败");//只有RuntimeException和继承自RuntimeException才能终止事务
            } else {
                if (shopImg != null) {
                    //存储图片
                    try{
                        addShopImg(shop, shopImg);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error:"+e.getMessage());
                    }
                    //更新店铺的图片信息
                    effectNum = shopDao.updateShop(shop);
                    if(effectNum<=0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("add shop error:"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop,File shopImg){
        //获取shop图片目录的相对路径
        System.out.println(shopImg.getName());
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generatorThumbnails(shopImg,dest);
        shop.setShopImg(shopImgAddr);
    }
}
