package org.qyq.`as`.proj.hi_library.util

import android.app.Application

/**
 *
 * @Author: Net Spirit
 * @Time: 2023/3/12 22:31
 * @FixAuthor:
 * @FixTime:
 * @Desc:获取全局application
 *
 */
object AppGlobals {
    var application: Application? = null
    fun get(): Application? {
        if (application == null) {
            try {
                application = Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null) as Application
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return application
    }
}