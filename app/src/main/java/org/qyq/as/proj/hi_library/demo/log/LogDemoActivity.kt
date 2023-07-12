package org.qyq.`as`.proj.hi_library.demo.log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.qyq.`as`.proj.hi_library.R
import org.qyq.`as`.proj.hi_library.log.HiLog
import org.qyq.`as`.proj.hi_library.log.HiLogConfig
import org.qyq.`as`.proj.hi_library.log.HiLogManager
import org.qyq.`as`.proj.hi_library.log.HiLogType
import org.qyq.`as`.proj.hi_library.log.pritenter.HiViewPrinter

class LogDemoActivity : AppCompatActivity() {
    var viewPrinter: HiViewPrinter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_demo)
        viewPrinter = HiViewPrinter(this)

        findViewById<Button>(R.id.btn_log).setOnClickListener {
            printLog()
        }
        viewPrinter!!.viewProvider.showFloatingView()
    }

    private fun printLog() {
        HiLogManager.getInstance().addPrinter(viewPrinter)
        //自定义Log配置
        HiLog.log(object : HiLogConfig() {
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, HiLogType.E, "----------", "5555556666")
        HiLog.a("999000")
    }
}