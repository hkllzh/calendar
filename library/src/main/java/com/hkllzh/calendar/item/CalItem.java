package com.hkllzh.calendar.item;

import android.view.View;

import org.joda.time.DateTime;

/**
 * 日历单个格子
 * <p/>
 * lizheng -- 2015/11/23
 */
public interface CalItem {

    /**
     * @return item布局文件的layoutId
     */
    int getLayoutResId();

    /**
     * 初始化views
     */
    void onBindViews(final View root);

    /**
     * 根据数据来设置item的内部views
     *
     * @param time
     * @param position 当前adapter调用item的位置
     */
    void onUpdateViews(DateTime time, int position);
}
