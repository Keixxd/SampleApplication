package ru.keixd.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.keixd.sampleapp.presentation.screen.MainComposeView
import ru.keixd.sampleapp.viewmodel.SampleAppViewmodel

class SampleAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        val vm: SampleAppViewmodel by viewModels()

        setContent {
            MainComposeView(vm)
        }
    }



}