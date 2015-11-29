package com.hkllzh.calendar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.hkllzh.calendar.adapter.CalendarBaseAdapter;
import com.hkllzh.calendar.controller.CalController;

/**
 * 单个月份日历view
 * <p/>
 * lizheng -- 2015/11/21
 */
public class CalendarView extends FrameLayout implements CalController {
    private static final String TAG = "CalendarView";
    private AdapterDataSetObserver mDataSetObserver;
    private CalendarBaseAdapter mAdapter;
    private int W;
    private int H;

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CalendarBaseAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(CalendarBaseAdapter adapter) {
        this.mAdapter = adapter;
        if (null != mAdapter) {
            if (null != mDataSetObserver) {
                mAdapter.unregisterDataSetObserver(mDataSetObserver);
            }
            resetCalView();
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
        addViews();
        requestLayout();
    }

    private void addViews() {
        int cellW = W / 7;
        int cellH = H / 7;
        int itemCount = mAdapter.getCount();
        if (49 == itemCount) {
            for (int i = 0; i < 49; i++) {
                View v = mAdapter.getView(i, null, this);
                addView(v, new LayoutParams(cellW, cellH));
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout");
        if (null == mAdapter) {
            return;
        }
        // addViews();
        int cellW = W / 7;
        int cellH = H / 7;
        int childViewCount = getChildCount();
        if (49 == childViewCount) {
            for (int i = 0; i < 49; i++) {
                View v = getChildAt(i);
                v.layout(cellW * getColumn(i), cellH * getRow(i), cellW * (getColumn(i) + 1), cellH * (getRow(i) + 1));
            }
        }
    }

    /**
     * 返回第几行
     *
     * @param position
     * @return
     */
    private int getRow(int position) {
        return position / 7;
    }

    /**
     * 返回第几列
     *
     * @param position
     * @return
     */
    private int getColumn(int position) {
        return position % 7;
    }

    private void resetCalView() {
        Log.e(TAG, "resetCalView");
        removeAllViewsInLayout();
        invalidate();
    }

    class AdapterDataSetObserver extends DataSetObserver {
        public void onChanged() {
            Log.e(TAG, "AdapterDataSetObserver#onChanged");
            requestLayout();
        }

        public void onInvalidated() {
            Log.e(TAG, "AdapterDataSetObserver#onInvalidated");
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = measure(widthMeasureSpec);
        int measuredHeight = measure(heightMeasureSpec);
        W = measuredWidth;
        H = measuredHeight;
        Log.d(TAG, "onMeasure - measuredWidth=" + measuredWidth +
                " -- measuredHeight" + measuredHeight);
        Log.d(TAG, "onMeasure - getMeasuredWidth=" + getMeasuredWidth() +
                " -- getMeasuredHeight=" + getMeasuredHeight());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int measure(int measureSpec) {
        int result = 0;
        // Decode the measurement specifications.
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
//        LogUtil.e(TAG + "measure - specMode=" + specMode + " - specSize=" + specSize);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "measure - specMode=UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "measure - specMode=AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.d(TAG, "measure - specMode=EXACTLY");
                break;
            default:
                Log.d(TAG, "measure - specMode=switch default");
                break;
        }
        return specSize;
    }

    @Override
    public void toToday() {

    }

    @Override
    public void plusDays(int days) {

    }

    @Override
    public void minusDays(int days) {

    }

    @Override
    public void plusWeeks(int weeks) {

    }

    @Override
    public void minusWeeks(int weeks) {

    }

    @Override
    public void plusMonths(int months) {

    }

    @Override
    public void minusMonths(int months) {

    }

    @Override
    public void plusYears(int years) {

    }

    @Override
    public void minusYears(int years) {

    }
}

