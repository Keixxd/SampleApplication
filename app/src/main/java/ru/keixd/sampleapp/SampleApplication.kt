package ru.keixd.sampleapp

import android.app.Application
import ru.keixd.base.utils.isMainProcess
import timber.log.Timber

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (isMainProcess) {
            proceedInit()
        }
    }

    private fun proceedInit() {
        //init libs here
        initTimber()
    }

    private fun initTimber() {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}