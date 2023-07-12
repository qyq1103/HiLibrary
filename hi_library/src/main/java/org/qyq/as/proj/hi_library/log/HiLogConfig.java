package org.qyq.as.proj.hi_library.log;

/**
 * @Author: NetSpirit
 * @Time: 2023/3/6 21:11
 * @FixAuthor:
 * @FixTime:
 * @Desc: 日志配置
 */
public abstract class HiLogConfig {
    /**
     * 每一行显示的最大长度
     */
    public static int MAX_LEN = 512;
    /**
     * 线程格式化器（多次使用创建为单例）
     */
    static HiThreadFormatter HI_THREAD_FORMATTER = new HiThreadFormatter();
    /**
     * 堆栈格式化器（多次使用创建为单例）
     */
    static HiStackTraceFormatter HI_STACK_TRACE_FORMATTER = new HiStackTraceFormatter();

    /**
     * json解析工具注入
     *
     * @return 解析工具，例如Gson
     */
    public JsonParser injectJsonParser() {
        return null;
    }

    /**
     * 全局日志标签
     *
     * @return HiLog
     */
    public String getGlobalTag() {
        return "HiLog";
    }

    /**
     * 是否启用日志
     *
     * @return true:默认启用
     */
    public boolean enable() {
        return true;
    }

    /**
     * 导入线程日志
     *
     * @return false:默认不导入
     */
    public boolean includeThread() {
        return false;
    }

    /**
     * 堆栈深度
     *
     * @return 默认5
     */
    public int stackTraceDepth() {
        return 5;
    }

    /**
     * 打印器数组
     *
     * @return 默认空
     */
    public HiLogPrinter[] printers() {
        return null;
    }

    /**
     * Json数据解析接口
     */
    public interface JsonParser {
        /**
         * Json 解析为字符串
         *
         * @param src Json 数据
         * @return Json字符串
         */
        String toJson(Object src);
    }
}
