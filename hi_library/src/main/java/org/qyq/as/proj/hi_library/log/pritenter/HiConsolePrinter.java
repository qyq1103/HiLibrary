package org.qyq.as.proj.hi_library.log.pritenter;


import static org.qyq.as.proj.hi_library.log.HiLogConfig.MAX_LEN;

import android.util.Log;
import androidx.annotation.NonNull;

import org.qyq.as.proj.hi_library.log.HiLogConfig;
import org.qyq.as.proj.hi_library.log.HiLogPrinter;


/**
 * @Author: Net Spirit
 * @Time: 2023/3/12 21:36
 * @FixAuthor:
 * @FixTime:
 * @Desc: 控制台日志输出
 */
public class HiConsolePrinter implements HiLogPrinter {
    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        //获取打印长度
        int len = printString.length();
        //行数
        int countOfSub = len / MAX_LEN;
        //行数大于0
        if (countOfSub > 0) {
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                //调用系统日志打印器
                //截取字符串逐行打印（截取index到index+MAX_LEN）
                Log.println(level, tag, printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            //所有行数打印完后，将剩余日志打印完全
            if (index != len) {
                Log.println(level, tag, printString.substring(index, len));
            }
        } else {
            //日志不足一行
            Log.println(level, tag, printString);
        }
    }
}
