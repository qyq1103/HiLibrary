package org.qyq.as.proj.hi_library.log;


/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 21:32
 * @FixAuthor:
 * @FixTime:
 * @Desc: 堆栈日志
 */
public class HiStackTraceFormatter implements HiLogFormatter<StackTraceElement[]> {
    /**
     * 堆栈日志信息
     *
     * @param stackTrace 堆栈数组
     * @return 处理后的堆栈日志字符串
     */
    @Override
    public String formatter(StackTraceElement[] stackTrace) {
        StringBuffer sb = new StringBuffer(128);
        //堆栈日志是否为空
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        } else if (stackTrace.length == 1) {
            //最有一条堆栈信息
            return "\t-" + stackTrace[0].toString();
        } else {
            for (int i = 0, len = stackTrace.length; i < stackTrace.length; i++) {
                //第一条堆栈信息添加标注stackTrace
                if (i == 0) {
                    sb.append("stackTrace:\n");
                }
                if (i != len - 1) {
                    sb.append("\t├ ")
                            .append(stackTrace[i].toString())
                            .append("\n");
                } else {
                    //标识最后一条数据
                    sb.append("\t└ ")
                            .append(stackTrace[i].toString());

                }

            }
        }
        return sb.toString();
    }
}
