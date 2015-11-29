package com.hkllzh.commoncalendar;

import android.view.View;
import android.widget.TextView;

import com.hkllzh.calendar.item.CalItem;

import org.joda.time.DateTime;

/**
 * 日历的单个格子
 * <p/>
 * lizheng -- 2015/11/23
 */
public class TodayCalItem implements CalItem {

    TextView textView;

    @Override
    public int getLayoutResId() {
        return R.layout.cal_item_today;
    }

    @Override
    public void onBindViews(View root) {
        textView = (TextView) root.findViewById(R.id.tvTest);
    }

    @Override
    public void onUpdateViews(DateTime time, int position) {
        textView.setText("今天");
    }
}
