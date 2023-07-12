package org.qyq.as.proj.hi_library.log;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 21:02
 * @FixAuthor:
 * @FixTime:
 * @Desc: 日志格式化接口
 */
public interface HiLogFormatter<T> {
    /**
     * 接口方法
     *
     * @param data 泛型日志数据
     * @return 字符串
     */
    String formatter(T data);
}
