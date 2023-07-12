package org.qyq.`as`.proj.hi_library.fps

import android.view.Choreographer
import org.qyq.`as`.proj.hi_library.log.HiLog
import java.util.concurrent.TimeUnit

/**
 *
 * @Author: Net Spirit
 * @Time: 2023/3/22 21:53
 * @FixAuthor:
 * @FixTime:
 * @Desc:
 *
 */
class FrameMonitor : Choreographer.FrameCallback {
    private val choreographer = Choreographer.getInstance()
    private var frameStartTime: Long = 0 //这个是记录上一帧到达的时间戳
    private var frameCount = 0 //1s 内确切绘制了多少帧

    private var listeners = arrayListOf<FpsMonitor.FpsCallback>()

    override fun doFrame(frameTimeNanos: Long) {
        val currentTimeMills = TimeUnit.NANOSECONDS.toMillis(frameTimeNanos)
        if (frameTimeNanos > 0) {
            //计算两帧之间的时间差
            val timeSpan = currentTimeMills - frameStartTime
            //fps 每秒多少帧 frame pre second
            frameCount++
            if (timeSpan > 1000) {
                val fps = frameCount * 1000 / timeSpan.toDouble()
                HiLog.e("FrameMonitor", fps)
                for (listener in listeners) {
                    listener.onFrame(fps)
                }
                frameCount = 0
                frameStartTime = currentTimeMills
            }
        } else {
            frameStartTime = currentTimeMills
        }
        start()
    }

    fun start() {
        choreographer.postFrameCallback { this }
    }

    fun stop() {
        frameStartTime = 0
        listeners.clear()
        choreographer.removeFrameCallback { this }
    }

    fun addListener(listener: FpsMonitor.FpsCallback) {
        listeners.add(listener)
    }
}