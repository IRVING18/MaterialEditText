package com.material.demo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * Created by wangzheng on 2019/3/19 9:26 AM.
 * E-mail : ivring11@163.com
 **/
public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {
    public static final float          TEXT_SIZE   = DisplayUtils.dip2px(14);
    public static final float          TEXT_MARGIN = DisplayUtils.dip2px(10);
    private             Paint          mPaint;
    private             Rect           backgroundPadding;
    private             Rect           rectTextBounds;
    private             boolean        showTopTag  = false;
    private             float          fraction;
    private             ObjectAnimator mAnimator;
    private             boolean        useLabel    = true;

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }

    public MaterialEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextSize(TEXT_SIZE);

        getAttrs(context, attrs);

        setBackgroundPadding();

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (useLabel) {
                    if (TextUtils.isEmpty(s)) {
                        showTopTag = false;
                        getAnimator().reverse();

                    } else if (!showTopTag) {
                        showTopTag = true;
                        getAnimator().start();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useTopTagLabel, true);
        typedArray.recycle();
    }

    /**
     * 设置顶部label显示位置
     */
    private void setBackgroundPadding() {
        if (rectTextBounds == null)
            rectTextBounds = new Rect();
        //获取背景的padding，但是设置padding设置的view的padding，而背景的padding不会变。主要用来动态设置是否显示顶部文字的。
        if (backgroundPadding == null)
            backgroundPadding = new Rect();
        getBackground().getPadding(backgroundPadding);
        int paddingTop;
        if (useLabel) {
            paddingTop = (int) (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN);
        } else {
            paddingTop = backgroundPadding.top;
        }
        //设置view的Padding
        setPadding(getPaddingLeft(), paddingTop, getPaddingRight(), getPaddingBottom());
    }

    /**
     * 获取动画
     *
     * @return
     */
    private ObjectAnimator getAnimator() {
        if (mAnimator == null) {
            mAnimator = ObjectAnimator.ofFloat(this, "fraction", 0, 1);
        }

        return mAnimator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画顶部文字
        String hint = getHint().toString();
        mPaint.setAlpha((int) (fraction * 0xff));//0xff 就是255
        mPaint.getTextBounds(hint, 0, hint.length(), rectTextBounds);
        int y = (int) (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN) / 2 - (rectTextBounds.top + rectTextBounds.bottom) / 2;
        canvas.drawText(getHint(), 0, getHint().length(), getPaddingLeft(), y + (getHeight() - y) * (1 - fraction), mPaint);
    }

    /**
     * 是否使用Label功能
     */
    public void setUseTopTagLabel(boolean use) {
        useLabel = use;
        setBackgroundPadding();
    }
}
