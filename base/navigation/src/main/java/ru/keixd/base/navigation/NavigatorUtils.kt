package ru.keixd.base.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.keixd.base.navigation.entity.INavArgs
import ru.keixd.base.navigation.entity.NavInfo

public inline fun <reified NavArgs: INavArgs> NavGraphBuilder.screen(
    //navigator: Navigator, //todo for later use
    screen: NavInfo<NavArgs>,
    crossinline body: @Composable () -> Unit
) {
    composable(route = screen.route) {
        //todo init feature VM here
        body()
    }
}