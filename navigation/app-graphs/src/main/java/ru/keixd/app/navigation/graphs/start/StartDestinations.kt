package ru.keixd.app.navigation.graphs.start

import androidx.navigation.NavGraphBuilder
import ru.keixd.app.navigation.graphs.start.StartDestinations.screenName
import ru.keixd.base.navigation.entity.NavInfo
import ru.keixd.base.navigation.entity.NoNavArgs
import ru.keixd.base.navigation.screen
import ru.keixd.feature.start.presentation.StartView

object StartDestinations {
    val screenName = NavInfo<NoNavArgs>("startScreen")
}

fun NavGraphBuilder.startGraph() {
    screen<NoNavArgs>(
        screen = screenName,
        body = { StartView() }
    )
}