package com.appotronics.carplay_app.utils.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @desc: toast工具类
 * @author: yewei
 * @email: 11196649@qq.com
 * @tel: 18680333215
 * @data: 2023/1/5 13:56
 */
public class MyPadToastUtils {


    private static Context mContext;

    public static void setContext(Context mContext) {
        MyPadToastUtils.mContext = mContext;
    }

    public static void showLongToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public static void showCustomShortToast(String msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    public static void showCustomLongToast(String msg) {
        show(msg, Toast.LENGTH_LONG);
    }


    private static void show(String massage, int duration) {
        PadToasty.normal(mContext, massage).show();
    }
}
