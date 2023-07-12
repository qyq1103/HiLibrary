package org.qyq.as.proj.hi_library.log;

import androidx.annotation.NonNull;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 21:54
 * @FixAuthor:
 * @FixTime:
 * @Desc: 日志打印器接口
 */
public interface HiLogPrinter {
    /**
     * 打印器
     *
     * @param config      配置
     * @param level       等级
     * @param tag         标签
     * @param printString 日志内容
     */
    void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString);
}
