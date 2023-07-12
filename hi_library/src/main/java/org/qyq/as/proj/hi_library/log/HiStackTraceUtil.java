package org.qyq.as.proj.hi_library.log;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 21:15
 * @FixAuthor:
 * @FixTime:
 * @Desc: 堆栈信息处理工具
 */
public class HiStackTraceUtil {
    /**
     * 获取裁剪的真实堆栈信息
     * @param stacktrace 堆栈信息
     * @param ignorePackage 忽略包名
     * @param maxDepth 最大深度
     * @return 裁剪后真实的堆栈信息
     */
    public static StackTraceElement[] getCroppedRealStackTrace(StackTraceElement[] stacktrace, String ignorePackage, int maxDepth) {
        return cropStackTrace(getRealStackTrace(stacktrace, ignorePackage), maxDepth);
    }

    /**
     * 裁剪堆栈信息
     *
     * @param callStack 除忽略包名外的堆栈数组
     * @param maxDepth  最大深度
     * @return 裁剪处理后的堆栈日志
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth) {
        //获取堆栈信息的长度
        int realDepth = callStack.length;
        //判断最大深度maxDepth 是否大于0，即设置了最大深度
        if (maxDepth > 0) {
            //堆栈最大实际深度和设置的深度取最小深度作为实际深度
            realDepth = Math.min(maxDepth, realDepth);
        }
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }

    /**
     * 获取除忽略包名之外的堆栈信息
     *
     * @param stacktrace    堆栈数组
     * @param ignorePackage 忽略包名
     * @return 除忽略包名外的堆栈数组
     */
    private static StackTraceElement[] getRealStackTrace(StackTraceElement[] stacktrace, String ignorePackage) {
        //忽略深度
        int ignoreDepth = 0;
        //所有深度
        int allDepth = stacktrace.length;
        //类名
        String className;
        //计算需要忽略的深度
        for (int i = allDepth - 1; i >= 0; i--) {
            className = stacktrace[i].getClassName();
            if (ignorePackage != null && className.startsWith(ignorePackage)) {
                //忽略深度
                ignoreDepth += 1;
            }
        }
        //实际深度
        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        //数组拷贝
        System.arraycopy(stacktrace, ignoreDepth, realStack, 0, realDepth);
        return realStack;
    }
}
