package com.example.templateapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.templateapp.designsystem.DesignSystem
import com.example.templateapp.ui.screens.content.Content
import com.example.templateapp.ui.screens.home.Home
import com.example.templateapp.ui.screens.settings.Settings

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String = Route.TopLevelRoute.Home.name
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Route.TopLevelRoute.Home.name) {
            Home()
        }
        composable(Route.TopLevelRoute.Content.name) {
            Content()
        }
        composable(Route.TopLevelRoute.Settings.name) {
            Settings(
                goDesignSystem = {
                    navController.navigate(Route.NestedLevelRoute.DesignSystem.name)
                }
            )
        }
        composable(Route.NestedLevelRoute.DesignSystem.name) {
            DesignSystem()
        }
    }
}

fun NavController.navigateToTopLevelDestination(topLevelDestination: Route.TopLevelRoute) {
    val topLevelNavOptions = navOptions {
        popUpTo(Route.TopLevelRoute.Home.name) {
            saveState = false
        }
        launchSingleTop = true
        restoreState = false
    }

    when (topLevelDestination) {
        Route.TopLevelRoute.Home -> this.navigateToHome(topLevelNavOptions)
        Route.TopLevelRoute.Content -> this.navigateToContent(topLevelNavOptions)
        Route.TopLevelRoute.Settings -> this.navigateToSettings(topLevelNavOptions)
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(Route.TopLevelRoute.Home.name, navOptions)
}

fun NavController.navigateToContent(navOptions: NavOptions? = null) {
    this.navigate(Route.TopLevelRoute.Content.name, navOptions)
}

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(Route.TopLevelRoute.Settings.name, navOptions)
}