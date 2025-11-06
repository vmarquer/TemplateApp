package com.example.templateapp.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentPaste
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.templateapp.R


sealed class Route(
    val name : String,
    val showTopBar : Boolean,
    val showBottomBar: Boolean,
    @StringRes val displayName: Int? = null
) {
    sealed class TopLevelRoute(
        name: String,
        showTopBar: Boolean,
        showBottomBar: Boolean,
        displayName: Int,
        val iconId: ImageVector,
        @StringRes val titleTextId: Int
    ) : Route(name, showTopBar, showBottomBar, displayName) {
        data object Home : TopLevelRoute(
            name = "home",
            showTopBar = true,
            showBottomBar = true,
            iconId = Icons.Rounded.Home,
            titleTextId = R.string.home_title,
            displayName = R.string.home_title,
        )

        data object Content : TopLevelRoute(
            name = "content",
            showTopBar = true,
            showBottomBar = true,
            iconId = Icons.Rounded.ContentPaste,
            titleTextId = R.string.content_title,
            displayName = R.string.content_title,
        )

        data object Settings : TopLevelRoute(
            name = "settings",
            showTopBar = true,
            showBottomBar = true,
            iconId = Icons.Rounded.Settings,
            titleTextId = R.string.settings_title,
            displayName = R.string.settings_title,
        )

        companion object {
            val entries: List<TopLevelRoute> = listOf(Home, Content, Settings)
        }
    }

    sealed class NestedLevelRoute(
        name: String,
        showTopBar : Boolean,
        showBottomBar: Boolean,
        displayName: Int? = null,
        val parent: TopLevelRoute
    ) : Route(name, showTopBar, showBottomBar, displayName) {
        data object DesignSystem : NestedLevelRoute(
            name = "design-system",
            showTopBar = true,
            showBottomBar = false,
            parent = TopLevelRoute.Settings,
            displayName = R.string.design_system_title,
        )
    }

    companion object {
        fun getRoute(route: String?): Route? {
            return when (route) {
                TopLevelRoute.Home.name -> TopLevelRoute.Home
                TopLevelRoute.Content.name -> TopLevelRoute.Content
                TopLevelRoute.Settings.name -> TopLevelRoute.Settings
                NestedLevelRoute.DesignSystem.name -> NestedLevelRoute.DesignSystem
                else -> null
            }
        }
    }
}