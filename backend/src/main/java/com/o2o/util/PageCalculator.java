package com.o2o.util;

/**
 * Description:
 * User: Garvol
 * Date: 2018-03-27
 * Time: 14:33
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
