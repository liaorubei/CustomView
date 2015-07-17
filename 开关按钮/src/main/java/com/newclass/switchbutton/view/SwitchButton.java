package com.newclass.switchbutton.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liaorubei on 2015/7/15.
 * 要求有以下功能
 * 1.在XML文件定义功能
 * 2.使用自定义属性功能
 * 3.直接设置和取得当前状态的功能
 * 4.当状态发生改变时能够回调的功能
 */
public class SwitchButton extends View {

    private Bitmap foreground;
    private Bitmap background;
    private boolean on;
    private float currentX;
    private float maxX;
    private float minX;
    private float foreLeft;


    public SwitchButton(Context context) {
        super(context);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setForegroundResource(int resourceId) {
        foreground = BitmapFactory.decodeResource(getContext().getResources(), resourceId);
    }

    public void setBackgroundResource(int resourceId) {
        background = BitmapFactory.decodeResource(getContext().getResources(), resourceId);
    }

    public void setState(boolean isOn) {
        this.on = isOn;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        minX = 0;
        maxX = background.getWidth() - foreground.getWidth();
        setMeasuredDimension(background.getWidth(), foreground.getWidth());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //当在画布上画图像的时候可以不用画笔
        canvas.drawBitmap(background, 0, 0, null);//画背景
        canvas.drawBitmap(foreground, foreLeft, 0, null);//画按钮
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //getX(),相对于控件本身左上角,getRawX,相对于屏幕左上角,无论是否有标题栏,ActionBar
        currentX = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                foreLeft = calcForeLeft(currentX);
                break;
            case MotionEvent.ACTION_UP:
                foreLeft = currentX > background.getWidth() / 2 ? maxX : minX;

                boolean temp = foreLeft == maxX;

                if (this.on != temp) {
                    this.on = temp;
                    if (mOnStateChangeListener != null) {
                        mOnStateChangeListener.currentState(this.on);
                    }
                }
                break;
        }


        invalidate();
        return true;
    }

    private float calcForeLeft(float currentX) {
        float temp = currentX - (foreground.getWidth() / 2);
        temp = temp < minX ? minX : temp;
        temp = temp > maxX ? maxX : temp;
        return temp;
    }

    private OnStateChangeListener mOnStateChangeListener;

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        this.mOnStateChangeListener = listener;
    }

    public interface OnStateChangeListener {
        void currentState(boolean state);
    }


}
