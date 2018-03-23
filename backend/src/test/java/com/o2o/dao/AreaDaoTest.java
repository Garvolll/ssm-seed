package com.o2o.dao;

import com.o2o.BaseTest;
import com.o2o.entity.Area;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-22
 * Time: 13:13
 */
public class AreaDaoTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AreaDao areaDao;

    @Test
    public void queryArea() throws Exception {
        List<Area> list = areaDao.queryArea();
        logger.info("Areas={}",list);

    }

}