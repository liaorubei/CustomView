package com.newclass.switchbutton.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.LruCache;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liaorubei on 2015/7/15.
 */
public class SwitchButton extends View {

    private Bitmap foreground;
    private Bitmap background;
    private boolean on;
    private Paint paint;
    private float x1;
    private float x2;
    private float offset;

    public SwitchButton(Context context) {
        super(context);
        paint = new Paint();
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
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
        setMeasuredDimension(background.getWidth(), background.getHeight());
        //  super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawBitmap(background, 0, 0, paint);
        if (this.on) {
            canvas.drawBitmap(foreground, 0, 0, paint);
        } else {
            canvas.drawBitmap(foreground, offset, 0, paint);
        }

        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //   Volley k=new Volley();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //getX(),相对于控件本身左上角,getRawX,相对于屏幕左上角,无论是否有标题栏,ActionBar
                System.out.println("getY()=" + event.getY() + " getRawY()=" + event.getRawY());

                x1 = event.getX();

                break;
            case MotionEvent.ACTION_MOVE:

                x2 = event.getX();

                offset = x2 - x1;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                this.on = !this.on;
                invalidate();
                break;
        }


        return true;
    }

    private class BitmapCache {
        private LruCache<String, Bitmap> cache;

        public BitmapCache() {
            cache = new LruCache<String, Bitmap>(1) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        public Bitmap get(String key) {
            return cache.get(key);
        }


        public void put(String key, Bitmap value) {
            cache.put(key, value);
        }
    }
}
