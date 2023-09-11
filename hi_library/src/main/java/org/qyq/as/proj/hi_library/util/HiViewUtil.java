package org.qyq.as.proj.hi_library.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Net Spirit
 * @Time: 2023/5/28 22:25
 * @FixAuthor:
 * @FixTime:
 * @Desc: View工具类
 */
public class HiViewUtil {

    /**
     * 获取指定类型的子View
     *
     * @param group viewGroup
     * @param cls   子View的类型 如：RecyclerView。class
     * @param <T>   指定类型
     * @return 指定类型的view
     */
    public static <T> T findTypeView(@Nullable ViewGroup group, Class<T> cls) {
        if (group == null) {
            return null;
        }
        //双端队列
        Deque<View> deque = new ArrayDeque<>();
        deque.add(group);
        while (!deque.isEmpty()) {
            View node = deque.removeFirst();
            if (cls.isInstance(node)) {
                return cls.cast(node);
            } else if (node instanceof ViewGroup) {
                ViewGroup container = (ViewGroup) node;
                for (int i = 0, count = container.getChildCount(); i < count; i++) {
                    deque.add(container.getChildAt(i));
                }
            }
        }
        return null;
    }

    public static boolean isActivityDestroyed(Context context) {
        Activity activity = findActivity(context);
        if (activity != null) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return activity.isDestroyed() || activity.isFinishing();
            }
            return activity.isFinishing();
        }
        return true;

    }

    /**
     * 检测是否是浅色主题
     *
     * @return true:浅色主题
     */
    public static boolean isLightMode() {
        int mode = AppGlobals.INSTANCE.get().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return mode == Configuration.UI_MODE_NIGHT_NO;
    }

    private static Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }
}
