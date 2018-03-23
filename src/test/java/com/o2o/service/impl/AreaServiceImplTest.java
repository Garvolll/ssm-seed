package com.o2o.service.impl;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import com.o2o.service.AreaService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-22
 * Time: 13:37
 */
public class AreaServiceImplTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AreaService areaService;

    @Test
    public void getAreaList() throws Exception {
        List<Area> res = areaService.getAreaList();
        logger.info("res={}",res.get(0));
    }

}