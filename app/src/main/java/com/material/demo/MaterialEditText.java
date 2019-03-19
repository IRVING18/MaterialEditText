package com.material.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by wangzheng on 2019/3/19 9:26 AM.
 * E-mail : ivring11@163.com
 **/
public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {
    public static final float TEXT_SIZE   = DisplayUtils.dip2px(14);
    public static final float TEXT_MARGIN = DisplayUtils.dip2px(10);
    private             Paint mPaint;
    private             Rect  backgroundPadding;
    private             Rect  rectTextBounds;

    public MaterialEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextSize(TEXT_SIZE);
        rectTextBounds = new Rect();

        //获取背景的padding，但是设置padding设置的view的padding，而背景的padding不会变。主要用来动态设置是否显示顶部文字的。
        backgroundPadding = new Rect();
        getBackground().getPadding(backgroundPadding);
        //设置view的Padding
        setPadding(getPaddingLeft(), (int) (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN), getPaddingRight(), getPaddingBottom());


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String hint = getHint().toString();
        mPaint.getTextBounds(hint, 0, hint.length(), rectTextBounds);
        int y = (int) (backgroundPadding.top +TEXT_SIZE + TEXT_MARGIN) / 2 - (rectTextBounds.top + rectTextBounds.bottom) / 2;
        canvas.drawText(getHint(), 0, getHint().length(), getPaddingLeft(), y, mPaint);
    }
}
