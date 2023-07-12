package org.qyq.as.proj.hi_library.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowMetrics;

import androidx.annotation.NonNull;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/12 21:52
 * @FixAuthor:
 * @FixTime:
 * @Desc: 1、获取屏幕宽高
 * 2、dp 转 px
 * 3、sp 转 px
 */
public class HiDisplayUtil {

    /**
     * 将dp 转为 px
     *
     * @param dp        要转换的尺寸
     * @param resources Resources
     * @return px像素
     */
    public static int dp2px(float dp, Resources resources) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static int dp2px(float dp) {
        Resources resources = AppGlobals.INSTANCE.get().getResources();
        return dp2px(dp, resources);
    }

    /**
     * sp 转为 px
     *
     * @param sp        要转换的尺寸
     * @param resources Resources
     * @return px 像素
     */
    public static int sp2px(float sp, Resources resources) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.getDisplayMetrics());
    }

    public static int sp2px(float sp) {
        Resources resources = AppGlobals.INSTANCE.get().getResources();
        return sp2px(sp, resources);
    }

    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度 (单位Px)
     */
    public static int getDisplayWidthInPx(@NonNull Context context) {
        //获取窗口管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //获取不到返回0
        if (wm == null) {
            return 0;
        }
        //android sdk版本大于30
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics metrics = wm.getCurrentWindowMetrics();
            //返回屏幕宽度
            return metrics.getBounds().width();
        }
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //屏幕宽度
        return size.x;

    }

    /**
     * 获取屏幕高度
     *
     * @param context 上下文
     * @return 屏幕宽度 (单位Px)
     */
    public static int getDisplayHeightInPx(@NonNull Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics metrics = wm.getCurrentWindowMetrics();
            return metrics.getBounds().height();
        }
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //屏幕高度
        return size.y;

    }
}
