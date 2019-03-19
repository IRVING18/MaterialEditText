package com.material.demo;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by wangzheng on 2019/3/11 7:17 PM.
 * E-mail : ivring11@163.com
 **/
public class DisplayUtils {

    public static float dip2px(float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
    }
}
