package com.o2o.service.impl;

import com.o2o.Exception.ShopCategoryOperationException;
import com.o2o.dao.ShopCategoryDao;
import com.o2o.dto.ShopCategoryExecution;
import com.o2o.entity.ShopCategory;
import com.o2o.enums.ShopCategoryStateEnum;
import com.o2o.service.ShopCategoryService;
import com.o2o.util.ImageUtil;
import com.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
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

    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
        return shopCategoryList;
    }

    @Override
    public ShopCategoryExecution getShopCategoryById(long shopCategoryId) {
        ShopCategory shopCategory = shopCategoryDao.queryShopCategoryById(shopCategoryId);
        return new ShopCategoryExecution(ShopCategoryStateEnum.SUCCESS, shopCategory);
    }

    @Override
    @Transactional
    public ShopCategoryExecution insertShopCategory(ShopCategory shopCategoryCondition, InputStream shopCategoryImg, String fileName) {
        if (shopCategoryCondition == null) {
            return new ShopCategoryExecution(ShopCategoryStateEnum.INNER_ERROR);
        }
        try {
            int effectNum = shopCategoryDao.insertShopCategory(shopCategoryCondition);
            if (effectNum < 0) {
                return new ShopCategoryExecution(ShopCategoryStateEnum.INNER_ERROR);
            } else {
                if (shopCategoryImg != null) {
                    try {
                        addImg(shopCategoryCondition, shopCategoryImg, fileName);
                    } catch (Exception e) {
                        throw new ShopCategoryOperationException("add shopCategoryImg error");
                    }
                    //更新店铺的图片信息
                    effectNum = shopCategoryDao.updateShopCategory(shopCategoryCondition);
                    if (effectNum <= 0) {
                        throw new ShopCategoryOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ShopCategoryOperationException("add shopCategory error");
        }
        return new ShopCategoryExecution(ShopCategoryStateEnum.SUCCESS, shopCategoryCondition);
    }

    private void addImg(ShopCategory shopCategory, InputStream shopCategoryImg, String fileName) {
        //获取shopCategory图片目录的相对路径
        String dest = PathUtil.getShopCategoryImgPath(shopCategory.getShopCategoryId());
        String shopImgAddr = ImageUtil.generatorThumbnails(shopCategoryImg, fileName, dest);
        shopCategory.setShopCategoryImg(shopImgAddr);
    }


    @Override
    public boolean deleteShopCategory(long shopCategoryId) {
        int effctNum = shopCategoryDao.deleteShopCategory(shopCategoryId);
        if (effctNum < 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    @Transactional
    public ShopCategoryExecution updateShopCategory(ShopCategory shopCategory, InputStream shopCategoryImg, String fileName) {
        if (shopCategory == null) {
            return new ShopCategoryExecution(ShopCategoryStateEnum.INNER_ERROR);
        }
        try {
            if (shopCategoryImg != null) {
                ShopCategory t_shopCategory = shopCategoryDao.queryShopCategoryById(shopCategory.getShopCategoryId());
                if (t_shopCategory.getShopCategoryImg() != null) {
                    ImageUtil.deleteFileOrPath(t_shopCategory.getShopCategoryImg());
                }
                addImg(shopCategory, shopCategoryImg, fileName);
            }
            shopCategory.setLastEditTime(new Date());
            int effectNum = shopCategoryDao.updateShopCategory(shopCategory);
            if (effectNum < 0) {
                return new ShopCategoryExecution(ShopCategoryStateEnum.INNER_ERROR);
            } else {
                shopCategory = shopCategoryDao.queryShopCategoryById(shopCategory.getShopCategoryId());
                return new ShopCategoryExecution(ShopCategoryStateEnum.SUCCESS, shopCategory);
            }
        } catch (Exception e) {
            throw new ShopCategoryOperationException("update shopCategory error");
        }
    }

}
