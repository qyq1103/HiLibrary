package org.qyq.as.proj.hi_library.log;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 21:05
 * @FixAuthor:
 * @FixTime:
 * @Desc: 日志类型
 */
public class HiLogType {
    /**
     * 在源码时期导入注解
     * 类型接收 V,D,I,E,W,A
     */
    @IntDef ({V, D, I, E, W, A})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    public static final int V = Log.VERBOSE;
    public static final int D = Log.DEBUG;
    public static final int I = Log.INFO;
    public static final int E = Log.ERROR;
    public static final int W = Log.WARN;
    public static final int A = Log.ASSERT;
}
