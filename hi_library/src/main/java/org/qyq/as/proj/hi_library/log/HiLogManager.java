package org.qyq.as.proj.hi_library.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Net Spirit
 * @Time: 2023/3/6 22:00
 * @FixAuthor:
 * @FixTime:
 * @Desc: 日志管理类
 */
public class HiLogManager {
    private HiLogConfig config;
    private static HiLogManager instance;
    private List<HiLogPrinter> printers = new ArrayList<>();

    private HiLogManager(HiLogConfig config, HiLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static HiLogManager getInstance() {
        return instance;
    }

    /**
     * 初始化
     *
     * @param config   配置
     * @param printers 打印器
     */
    public static void init(@NonNull HiLogConfig config, HiLogPrinter... printers) {
        instance = new HiLogManager(config, printers);
    }

    /**
     * 获取配置
     *
     * @return config
     */
    public HiLogConfig getConfig() {
        return config;
    }

    /**
     * 获取所有打印器
     *
     * @return printers
     */
    public List<HiLogPrinter> getPrinters() {
        return printers;
    }

    /**
     * 添加打印器
     *
     * @param printer 打印器
     */
    public void addPrinter(HiLogPrinter printer) {
        printers.add(printer);
    }

    /**
     * 移除打印器
     *
     * @param printer 要移除的打印器
     */
    public void removePrinter(HiLogPrinter printer) {
        printers.remove(printer);
    }
}
