package com.o2o.entity;

import java.util.Date;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-21
 * Time: 15:17
 */
public class WechatAuth {

    private Long wechatAuthId;

    private String openId;

    private Date createTime;

    private User user;

    public String getOpenId() {
        return openId;
    }

    public Long getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WechatAuth{" +
                "wechatAuthId=" + wechatAuthId +
                ", openId='" + openId + '\'' +
                ", createTime=" + createTime +
                ", user=" + user +
                '}';
    }
}
