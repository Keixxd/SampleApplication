package ru.keixd.navigation.core.api

import kotlinx.coroutines.flow.Flow
import ru.keixd.base.navigation.entity.INavArgs
import ru.keixd.base.navigation.entity.NavInfo
import ru.keixd.base.navigation.entity.NoNavArgs

interface INavigator{

    public fun <NavArgs: INavArgs> navigate(
        screen: NavInfo<NavArgs>,
        navArgs: NavArgs,
        tag: String? = null,
        singleInstance: Boolean,
        onComplete: (() -> Unit)? = null
    )

    public fun navigate(
        screen: NavInfo<NoNavArgs>,
        tag: String? = null,
        singleInstance: Boolean? = null,
        onComplete: (() -> Unit)? = null
    )

    public fun popBackStack(
        onComplete: (() -> Unit)? = null
    )

}