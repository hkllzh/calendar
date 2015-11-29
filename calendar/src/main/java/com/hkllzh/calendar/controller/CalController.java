package com.hkllzh.calendar.controller;

/**
 * 日历控制器
 * <p/>
 * lizheng -- 2015/11/21
 */
public interface CalController {

    void toToday();

    void plusDays(int days);

    void minusDays(int days);

    void plusWeeks(int weeks);

    void minusWeeks(int weeks);

    void plusMonths(int months);

    void minusMonths(int months);

    void plusYears(int years);

    void minusYears(int years);


}
