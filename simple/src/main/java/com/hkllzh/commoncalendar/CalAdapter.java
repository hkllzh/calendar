package com.hkllzh.commoncalendar;

import com.hkllzh.calendar.adapter.CalendarBaseAdapter;
import com.hkllzh.calendar.item.CalItem;

/**
 * 日历适配器
 * <p/>
 * lizheng -- 2015/11/23
 */
public class CalAdapter extends CalendarBaseAdapter {
    @Override
    protected CalItem getCommonItem() {
        return new CommonCalItem();
    }

    @Override
    protected CalItem getHeaderItem() {
        return getCommonItem();
    }

    @Override
    protected CalItem getTodayItem() {
        return new TodayCalItem();
    }

    @Override
    protected CalItem getSelectItem() {
        return new SelectCalItem();
    }
}
