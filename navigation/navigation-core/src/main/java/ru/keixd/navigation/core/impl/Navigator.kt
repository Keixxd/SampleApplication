package ru.keixd.navigation.core.impl

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import ru.keixd.base.navigation.entity.INavArgs
import ru.keixd.base.navigation.entity.NavInfo
import ru.keixd.base.navigation.entity.NoNavArgs
import ru.keixd.navigation.core.api.INavigator

class Navigator(
    private val navController: NavHostController,
    private val compositionStore: CompositionStore,
    private val coroutineScope: CoroutineScope
) : INavigator {

    override fun navigate(
        screen: NavInfo<NoNavArgs>,
        tag: String?,
        singleInstance: Boolean?,
        onComplete: (() -> Unit)?
    ) {
        navigate(
            screen = screen,
            navArgs = NoNavArgs,
            tag = tag,
            singleInstance = false,
            onComplete = onComplete
        )
    }


    override fun <NavArgs : INavArgs> navigate(
        screen: NavInfo<NavArgs>,
        navArgs: NavArgs,
        tag: String?,
        singleInstance: Boolean,
        onComplete: (() -> Unit)?
    ) {
        navigateInternal(
            screen = screen,
            navArgs = navArgs,
            tag = tag,
            singleInstance = singleInstance,
            onComplete = onComplete
        )
    }

    override fun popBackStack(onComplete: (() -> Unit)?) {
        coroutineScope.launch {
            val targetEntry = navController.previousBackStackEntry

            if (targetEntry == null) {
                onComplete?.invoke()
                return@launch
            }

            compositionStore
                .screens
                .filter { it.contains(targetEntry.id) }
                .onStart { navController.popBackStack() }
                .take(1)
                .onEach {
                    onComplete?.invoke()
                }
                .collect()
        }
    }

    private fun <NavArgs: INavArgs>navigateInternal(
        screen: NavInfo<NavArgs>,
        navArgs: NavArgs,
        singleInstance: Boolean,
        tag: String?,
        onComplete: (() -> Unit)?,
    ) {
        val route = screen.route

        coroutineScope.launch {
            navController
                .currentBackStackEntryFlow
                .filter { entry -> route == entry.destination.route }
                .combine(compositionStore.screens) { entry, screens ->
                    entry.id in screens
                }
                .filter { it }
                .take(1)
                .onEach { onComplete?.invoke() }
                .onStart { navController.navigate(route = route) }
                .collect()
        }
    }
}

@PublishedApi
internal val LocalNavigator: ProvidableCompositionLocal<INavigator?> =
    compositionLocalOf { null }