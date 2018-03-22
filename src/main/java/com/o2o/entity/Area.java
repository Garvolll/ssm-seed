package com.o2o.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Description: 区域信息实体类
 * User: Garvol
 * Date: 2018-03-21
 * Time: 14:06
 */
public class Area {

    private Integer id;
    //区域名称
    private String areaName;
    //权重
    private Integer priority;
    //创建时间
    private Date  creatTime;
    //更新时间
    private Date lastTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", areaName='" + areaName + '\'' +
                ", priority=" + priority +
                ", creatTime=" + creatTime +
                ", lastTime=" + lastTime +
                '}';
    }
}
