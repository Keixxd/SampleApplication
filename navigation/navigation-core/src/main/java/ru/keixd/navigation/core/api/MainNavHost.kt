package ru.keixd.navigation.core.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.keixd.app.navigation.graphs.start.StartDestinations
import ru.keixd.app.navigation.graphs.start.startGraph
import ru.keixd.base.navigation.entity.MainHostState
import ru.keixd.navigation.core.impl.LocalCompositionStore
import ru.keixd.navigation.core.impl.LocalNavigator
import ru.keixd.navigation.core.impl.Navigator

@Composable
fun MainNavHost(state: MainHostState) {
    InitNavigator { navHostController, navigator ->
        MainNavHost(
            navController = navHostController,
            navigator = navigator,
            isClearNavigationStack = state.isClearNavigationStack
        )
    }
}

@Composable
private fun MainNavHost(
    navController: NavHostController,
    navigator: INavigator,
    isClearNavigationStack: Boolean
) {
    NavHost(navController = navController, startDestination = StartDestinations.screenName.route) {
        startGraph()
    }

    LaunchedEffect(isClearNavigationStack) {
        if(isClearNavigationStack) {
            navigator.navigate(
                screen = StartDestinations.screenName
            )
        }
    }
}

@Composable
private fun InitNavigator(body: @Composable (NavHostController, INavigator) -> Unit) {

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    val navigator = Navigator(
        navController = navController,
        compositionStore = LocalCompositionStore.current,
        coroutineScope = coroutineScope
    )

    CompositionLocalProvider(
        LocalNavigator provides navigator
    ) {
        body(navController, navigator)
    }

}