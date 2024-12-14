package ru.keixd.base.navigation.entity

public class NavInfo<NavArgs>(private val screenId: String) {
    public val route: String = createRoute(screenId)
}

private fun createRoute(screenId: String) = "/$screenId"