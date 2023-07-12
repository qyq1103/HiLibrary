package org.qyq.as.proj.hi_library.log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 22:15
 * @FixAuthor:
 * @FixTime:
 * @Desc: 日志：
 * 1、打印堆栈信息
 * 2、File输出
 * 3、模拟控制台
 */
public class HiLog {
    private static final String HI_LOG_PACKAGE;

    static {
        String className = HiLog.class.getName();
        HI_LOG_PACKAGE = className.substring(0, className.lastIndexOf(".") + 1);
    }

    public static void v(Object... contents) {
        log(HiLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(HiLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(HiLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(HiLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(HiLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(HiLogType.I, tag, contents);
    }

    public static void e(Object... contents) {
        log(HiLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(HiLogType.E, tag, contents);
    }

    public static void w(Object... contents) {
        log(HiLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(HiLogType.W, tag, contents);
    }

    public static void a(Object... contents) {
        log(HiLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(HiLogType.A, tag, contents);
    }

    public static void log(@HiLogType.TYPE int type, Object... contents) {
        log(type, HiLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@HiLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(HiLogManager.getInstance().getConfig(), type, HiLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    /**
     * 日志输出
     *
     * @param config   配置
     * @param type     (类型)等级
     * @param tag      tag
     * @param contents 日志内容
     */
    public static void log(@NonNull HiLogConfig config, @HiLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            //是否开启HiLog日志，未开启直接return
            return;
        }
        //日志拼接
        StringBuilder sb = new StringBuilder();
        //是否开启线程日志
        if (config.includeThread()) {
            //获取格式化后的线程日志
            String threadInfo = HiLogConfig.HI_THREAD_FORMATTER.formatter(Thread.currentThread());
            sb.append(threadInfo)
                    .append("\n");
        }
        //是否有堆栈日志
        if (config.stackTraceDepth() > 0) {
            //获取格式化后的堆栈日志
            String stackTrace = HiLogConfig.HI_STACK_TRACE_FORMATTER.
                    formatter(HiStackTraceUtil.getCroppedRealStackTrace(
                            new Throwable().getStackTrace(),
                            HI_LOG_PACKAGE,
                            config.stackTraceDepth())
                    );
            sb.append(stackTrace)
                    .append("\n");
        }
        //格式化后的其他日志（自定义打印信息等）
        String body = parseBody(contents, config);
        sb.append(body);
        //获取打印器
        List<HiLogPrinter> printers = config.printers() != null ? Arrays.asList(config.printers()) : HiLogManager.getInstance().getPrinters();
        //打印器为空，直接return
        if (printers == null) {
            return;
        }
        for (HiLogPrinter printer : printers) {
            //逐一使用打印器打印日志
            printer.print(config, type, tag, sb.toString());
        }
    }

    /**
     * 解析格式化其他日志
     *
     * @param contents 日志数组
     * @param config   配置 （通过配置获取Json格式化工具，例如Gson）
     * @return 格式化后的字符串
     */
    private static String parseBody(@NonNull Object[] contents, @NonNull HiLogConfig config) {
        if (config.injectJsonParser() != null) {
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString())
                    .append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


}
