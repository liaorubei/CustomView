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
 */
public class SwitchButton extends View {

    private Bitmap foreground;
    private Bitmap background;
    private boolean on;
    private float currentX;
    private float maxX;
    private float minX;
    private float foreLeft;
    private int forewidth;
    private int backwidth;


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
        forewidth = foreground.getWidth();
        backwidth = background.getWidth();

        minX = 0;
        maxX = backwidth - forewidth;
        setMeasuredDimension(backwidth, backwidth);
        //  super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

//当在画布上画图像的时候可以不用画笔
        canvas.drawBitmap(background, 0, 0, null);//画背景

        foreLeft = currentX - (foreground.getWidth() / 2);//计算前景左边坐标
        if (foreLeft < minX) {
            foreLeft = minX;
            this.on = false;
        }

        if (foreLeft > maxX) {
            foreLeft = maxX;
            this.on = true;
        }
        canvas.drawBitmap(foreground, foreLeft, 0, null);

        System.out.println("this.on=" + this.on);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)  {

        //getX(),相对于控件本身左上角,getRawX,相对于屏幕左上角,无论是否有标题栏,ActionBar
        currentX = event.getX();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                currentX = currentX > backwidth / 2 ? backwidth / 2 + forewidth / 2 : backwidth / 2 - forewidth / 2;
                break;
        }
        invalidate();
        return true;
    }


}
