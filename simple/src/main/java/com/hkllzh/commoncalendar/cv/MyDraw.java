package com.hkllzh.commoncalendar.cv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

public class MyDraw extends View {

    private static final String TAG = "MyDraw";

    private static int ALL_LENGTH = 3600;// 总共的绘制量
    private int currentLength = 0; // 当然的绘制量

    private Scroller mScroller; // 绘制控制器
    private Paint paintCircle; // 中间的圆
    private Paint paintSelect; // 选择的区域
    private RectF f;

    private int width;
    private int height;

    public MyDraw(Context context) {
        super(context);
        init();
    }

    public MyDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        mScroller = new Scroller(getContext(), new LinearInterpolator());
//        mScroller = new Scroller(getContext());
        paintCircle = new Paint();
        paintSelect = new Paint();
        paintSelect.setAntiAlias(true);
        paintSelect.setColor(Color.RED);// 设置绿色
        paintSelect.setStrokeWidth(10);
        setLayerType(LAYER_TYPE_HARDWARE, new Paint());
    }

    boolean isStart = false;

    public void start() {
        isStart = true;
        i = 0;
        mScroller.startScroll(0, 0, 0, 900, 1000);
        postInvalidate();
    }

    int i = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // canvas.drawColor(Color.parseColor("#999999"));
         canvas.drawLine(0, 0, 900, 900, paintSelect);// 画线
         canvas.drawLine(900, 0, 0, 900, paintSelect);// 画线
        if (0 == width || width != getWidth()) {
            width = getWidth();
        }
        if (0 == height || height != getHeight()) {
            height = getHeight();
        }

        int w = getWidth();
        int h = getHeight();

        float l = w * ((float) mScroller.getCurrY() / ALL_LENGTH);
        Log.e(TAG, "w:" + w + " - h:" + h + " - i:" + i + " - y:" + mScroller.getCurrY() + " - l:" + l);
        if (isStart) {

            i += 1;
            if (i >= width) {
                isStart = false;
            } else {
                // invalidate();
                // postInvalidate();
            }
        }


//        // 将圆心设置在屏幕中心
//        int pointWidth = w;
//        int pointHeight = h;
//
//        paint.setColor(Color.rgb(220, 220, 220));
//        canvas.drawCircle(pointWidth, pointHeight, 100, paint);
//        paint.setColor(Color.RED);
//
//        f = new RectF(pointWidth - 100, pointHeight - 100,
//                pointWidth + 100, pointHeight + 100);
//        canvas.drawArc(f, -90f, i, true, paint);
//
//        paint.setColor(Color.WHITE);
//        canvas.drawCircle(pointWidth, pointHeight, 80, paint);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            // this.postInvalidate();
        }
    }
}