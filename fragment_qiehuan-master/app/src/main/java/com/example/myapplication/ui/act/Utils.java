package com.example.myapplication.ui.act;

import android.content.Context;

public class Utils {

    //dp转化为px
    public static int convertDpToPixel(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  //屏幕密度
        return (int) (dpValue * scale + 0.5f);  //加0.5四舍五入
    }

    //px转化为dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
