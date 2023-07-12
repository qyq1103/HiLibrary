package org.qyq.as.proj.hi_library.log;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 21:13
 * @FixAuthor:
 * @FixTime:
 * @Desc: 线程日志
 */
public class HiThreadFormatter implements HiLogFormatter<Thread> {
    /**
     * 线程日志信息
     *
     * @param data 线程
     * @return 线程名
     */
    @Override
    public String formatter(Thread data) {
        return "Thread:" + data.getName();
    }
}
