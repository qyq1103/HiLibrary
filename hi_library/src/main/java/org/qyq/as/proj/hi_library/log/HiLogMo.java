package org.qyq.as.proj.hi_library.log;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 20:43
 * @FixAuthor:
 * @FixTime:
 * @Desc: 日志格式实体类
 */
public class HiLogMo {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
    //时间戳
    public long timeMillis;
    //日志等级
    public int level;
    //日志标签
    public String tag;
    //日志内容
    public String log;

    /**
     * 日志实体构造方法
     *
     * @param timeMillis 时间戳
     * @param level      日志等级
     * @param tag        日志标签
     * @param log        日志内容
     */
    public HiLogMo(long timeMillis, int level, String tag, String log) {
        this.timeMillis = timeMillis;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    /**
     * 扁平化日志
     *
     * @return 时间 | 日志等级 | 日志标签 |：日志内容
     */
    public String flattenedLog() {
        return getFlattened() + "\n" + log;
    }

    /**
     * 拼接日志输出格式
     *
     * @return 时间 | 日志等级 | 日志标签 |：
     */
    public String getFlattened() {
        return format(timeMillis) + "|" + level + "|" + tag + "|:";
    }

    /**
     * 格式化时间戳
     *
     * @param timeMillis 时间戳
     * @return yy-MM-dd HH:mm:ss 时间字符串
     */
    private String format(long timeMillis) {
        return sdf.format(timeMillis);
    }
}
