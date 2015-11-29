package com.hkllzh.calendar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hkllzh.calendar.annotation.Week;
import com.hkllzh.calendar.item.CalItem;

import org.joda.time.DateTime;


/**
 * 日历BaseAdapter
 * <p/>
 * 位置如下所标，但是第一行为显示周几所用，不计入position。
 * 此格子数目为49个，而不是只有日历区域的42个。
 * <pre>
 * position
 * 0  1  2  3  4  5  6
 * 7  8  9  10 11 12 13
 * 14 15 16 17 18 19 20
 * 21 22 23 24 25 26 27
 * 28 29 30 31 32 33 34
 * 35 36 37 38 39 40 41
 * </pre>
 * 第一行一定有周一，最后一行有可能全部是下个月的数据
 * lizheng -- 2015/11/21
 */
public abstract class CalendarBaseAdapter extends BaseAdapter {

    private static final int COLUMN_NUM = 7;
    private static final int ROW_NUM = 7;

    private static final String PATTERN = "yyyy-MM-dd";

    private LayoutInflater mInflater;

    private DateTime mSelectDateTime; // 选择的时间
    private int selectPosition; // 选择的时间的position
    private DateTime showYearAndMonth; // 要显示的是那个月
    private DateTime todayDateTime; // 今天的时间
    private String todayDateTimeString; // 今天时间的字符串（格式化为yyyy-MM-dd）
    private int firstDayOfWeek;// 这个月的第一天是周几(1--7)
    private int mStartWeek;// 第一列是周几，默认周一


    public CalendarBaseAdapter() {
        todayDateTime = DateTime.now();
        todayDateTimeString = todayDateTime.toString(PATTERN);
        mStartWeek = Week.MONDAY;
    }

    public void setSelectData(DateTime dataTime, @Week int startWeek) {
        this.mSelectDateTime = dataTime;
        this.mStartWeek = startWeek;

        showYearAndMonth = new DateTime(mSelectDateTime.getYear(), mSelectDateTime.getMonthOfYear(), 1, 0, 0, 0);
        firstDayOfWeek = showYearAndMonth.getDayOfWeek();
        selectPosition = dateTime2Position(mSelectDateTime);
    }

    /**
     * 返回DateTime对应的position（位置从0开始）
     *
     * @param dateTime
     * @return 位置，从0开始
     */
    private int dateTime2Position(DateTime dateTime) {
        return dateTime.getDayOfMonth() + getStartOffset() - 1 + 7;
    }

    /**
     * 获取偏移量
     * 每月1号，前面有几个空格子
     *
     * @return
     */
    private int getStartOffset() {
        int temp = firstDayOfWeek - mStartWeek;
        return temp > -1 ? temp : 7 + temp;
    }

    public void setSelectData(DateTime dataTime) {
        setSelectData(dataTime, Week.MONDAY);
    }

    @Override
    public int getCount() {
        if (null == mSelectDateTime) {
            return 0;
        }
        return ROW_NUM * COLUMN_NUM;
    }

    @Override
    public DateTime getItem(int position) {
        // 每月一号、偏移量、position、第一行
        return showYearAndMonth.plusDays(position - getStartOffset() - 7);
    }

    @Override
    @Deprecated
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        DateTime dateTime = getItem(position);
        CalItem item;
        if (position < 7) {
            item = getHeaderItem();
        } else if (todayDateTimeString.equals(dateTime.toString(PATTERN))) {
            item = getTodayItem();
            if (null == item) {
                item = getCommonItem();
            }
        } else if (mSelectDateTime.toString(PATTERN).equals(dateTime.toString(PATTERN))) {
            item = getSelectItem();
            if (null == item) {
                item = getCommonItem();
            }
        } else {
            item = getCommonItem();
        }

        convertView = mInflater.inflate(item.getLayoutResId(), parent, false);
        // convertView = View.inflate(parent.getContext(),item.getLayoutResId(),null);
        item.onBindViews(convertView);
        item.onUpdateViews(dateTime, position);
        return convertView;
    }

    /**
     * 当前item，为空，使用通用{@link #getCommonItem()}
     * @return
     */
    protected CalItem getTodayItem() {
        return null;
    }

    /**
     * 所选item，为空，使用通用{@link #getCommonItem()}
     * @return
     */
    protected CalItem getSelectItem() {
        return null;
    }

    /**
     * 通用item
     * @return
     */
    protected abstract CalItem getCommonItem();

    /**
     * 头部item
     * @return
     */
    protected abstract CalItem getHeaderItem();
}
