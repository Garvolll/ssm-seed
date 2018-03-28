package com.o2o.dto;

import com.o2o.entity.ShopCategory;
import com.o2o.enums.ShopCategoryStateEnum;

import java.util.List;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-28
 * Time: 10:33
 */
public class ShopCategoryExecution {
    private int state;
    private String stateInfo;
    private int count;
    private ShopCategory shopCategory;
    private List<ShopCategory> shopCategoryList;

    public ShopCategoryExecution(ShopCategoryStateEnum shopCategoryStateEnum, ShopCategory shopCategory) {
        this.state = shopCategoryStateEnum.getState();
        this.stateInfo = shopCategoryStateEnum.getStateInfo();
        this.shopCategory = shopCategory;
    }

    public ShopCategoryExecution(ShopCategoryStateEnum shopCategoryStateEnum, List<ShopCategory> shopCategoryList) {
        this.state = shopCategoryStateEnum.getState();
        this.stateInfo = shopCategoryStateEnum.getStateInfo();
        this.shopCategoryList = shopCategoryList;
    }

    public ShopCategoryExecution(ShopCategoryStateEnum shopCategoryStateEnum) {
        this.state = shopCategoryStateEnum.getState();
        this.stateInfo = shopCategoryStateEnum.getStateInfo();
        this.stateInfo = stateInfo;
    }

    public ShopCategoryExecution() {

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    public List<ShopCategory> getShopCategoryList() {
        return shopCategoryList;
    }

    public void setShopCategoryList(List<ShopCategory> shopCategoryList) {
        this.shopCategoryList = shopCategoryList;
    }
}
