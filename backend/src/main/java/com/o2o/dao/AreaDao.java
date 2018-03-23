package com.o2o.dao;

import com.o2o.entity.Area;

import java.util.List;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-22
 * Time: 10:39
 */
public interface AreaDao {
    /** 
    * @Description: 查询所有区域 
    * @Param: areaList
    */ 
    List<Area> queryArea();
}
