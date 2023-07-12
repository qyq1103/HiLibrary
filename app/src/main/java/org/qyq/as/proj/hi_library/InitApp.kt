package org.qyq.`as`.proj.hi_library

import android.app.Application
import com.google.gson.Gson
import org.qyq.`as`.proj.hi_library.log.HiLogConfig
import org.qyq.`as`.proj.hi_library.log.HiLogManager
import org.qyq.`as`.proj.hi_library.log.pritenter.HiConsolePrinter

/**
 *
 * @Author: Net Spirit
 * @Time: 2023/3/19 22:33
 * @FixAuthor:
 * @FixTime:
 * @Desc:
 *
 */
class InitApp : Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.init(object : HiLogConfig() {
            override fun injectJsonParser(): JsonParser {
                //添加序列化工具
                return JsonParser { src -> Gson().toJson(src) }
            }

            override fun getGlobalTag(): String {
                //全局TAG
                return "HiLibraryDemo"
            }

            override fun enable(): Boolean {
                //开启日志打印
                return true
            }
        }, HiConsolePrinter()) //控制台打印器添加
    }
}