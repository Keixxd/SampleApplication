package ru.keixd.base.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Process

val Context.isMainProcess: Boolean
    get() {
        val processName = getProcess()?.name
        return if(processName != null) {
            packageName == processName
        } else {
            true
        }
    }

fun Context.getProcess() : ProcessInfo? {
    val pid = Process.myPid()
    val manager = this.getSystemService(Activity.ACTIVITY_SERVICE) as? ActivityManager
    val processes = manager?.runningAppProcesses
    processes?.forEach { info ->
        if(info.pid == pid){
            return ProcessInfo(name = info.processName)
        }
    }
    return null
}

data class ProcessInfo(val name: String)