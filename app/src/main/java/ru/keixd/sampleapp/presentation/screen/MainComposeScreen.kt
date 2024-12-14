package ru.keixd.sampleapp.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.keixd.navigation.core.api.MainNavHost
import ru.keixd.base.navigation.entity.MainHostState
import ru.keixd.sampleapp.viewmodel.SampleAppViewmodel

@Composable
internal fun MainComposeView(vm: SampleAppViewmodel) {

    val state = vm.state.collectAsState().value

    MainNavHost(
        state = MainHostState(
            isDebug = state.isDebug,
            isClearNavigationStack = state.isClearNavigationStack
        )
    )
}