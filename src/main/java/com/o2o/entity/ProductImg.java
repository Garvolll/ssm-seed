package com.o2o.entity;

import java.util.Date;

/**
 * Description:商品详情图实体类
 * User: Garvol
 * Date: 2018-03-21
 * Time: 16:14
 */
public class ProductImg {

    private Long productImgId;

    private String imgAddr;

    private String imgDesc;

    private Integer priority;

    private Date createTime;
    // 标明是属于哪个商品的图片
    private Long productId;

    public Long getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductImg{" +
                "productImgId=" + productImgId +
                ", imgAddr='" + imgAddr + '\'' +
                ", imgDesc='" + imgDesc + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", productId=" + productId +
                '}';
    }
}
