package ru.keixd.sampleapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.keixd.base.navigation.entity.MainHostState
import ru.keixd.sampleapp.BuildConfig

class SampleAppViewmodel : ViewModel() {

    private val _state = MutableStateFlow<MainHostState>(
        MainHostState(
            isDebug = BuildConfig.DEBUG,
            isClearNavigationStack = true
        )
    )
    internal val state = _state.asStateFlow()

}