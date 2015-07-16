package com.newclass.youkumenu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by liaorubei on 2015/7/16.
 */
public class YouKuMenu extends ViewGroup {
    public YouKuMenu(Context context) {
        super(context);
        init();
    }

    public YouKuMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public YouKuMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
