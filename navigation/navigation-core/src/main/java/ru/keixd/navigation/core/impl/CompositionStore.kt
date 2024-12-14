package ru.keixd.navigation.core.impl

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@PublishedApi
internal val LocalCompositionStore: ProvidableCompositionLocal<CompositionStore> = staticCompositionLocalOf { CompositionStore() }

@Stable
class CompositionStore {
    private val _screens = MutableStateFlow<Set<String>>(emptySet())
    internal val screens = _screens.asStateFlow()

    fun onEnterComposition(screen: String) {
        _screens.update { it + screen }
    }

    fun onExitComposition(screen: String) {
        _screens.update { it - screen }
    }
}