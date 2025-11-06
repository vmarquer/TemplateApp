package com.example.templateapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.templateapp.navigation.NavigationHost
import com.example.templateapp.navigation.Route
import com.example.templateapp.navigation.navigateToTopLevelDestination
import com.example.templateapp.ui.screens.content.Content
import com.example.templateapp.ui.screens.home.Home
import com.example.templateapp.ui.screens.settings.Settings
import com.example.templateapp.ui.screens.toast.GlobalToast
import com.example.templateapp.ui.theme.black
import com.example.templateapp.ui.theme.components.TextBottomBar
import com.example.templateapp.ui.theme.components.TextH3Bold
import com.example.templateapp.ui.theme.iconMediumSize
import com.example.templateapp.ui.theme.padding6
import com.example.templateapp.ui.theme.primary
import com.example.templateapp.ui.theme.white
import kotlinx.coroutines.launch

@Composable
fun App(
    appUIState: AppUIState = rememberAppUIState(),
) {
    Box(modifier = with(Modifier.background(MaterialTheme.colorScheme.background)) {
        fillMaxSize()
    }) {
        Scaffold(
            contentColor = Color.Transparent,
            containerColor = Color.Transparent,
            topBar = {
                TopBar(
                    currentDestination = appUIState.currentDestination,
                    onBack = { appUIState.navController.popBackStack() }
                )
            },
            bottomBar = {
                if (appUIState.shouldShowBottomBar) {
                    BottomBar(
                        destinations = appUIState.topLevelDestinations,
                        currentDestination = appUIState.currentDestination,
                        onNavigateToDestination = appUIState.navController::navigateToTopLevelDestination
                    )
                }
            },
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                GlobalToast()
                NavigationHost(navController = appUIState.navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    currentDestination: NavDestination?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentRoute = Route.getRoute(currentDestination?.route)

    currentRoute?.let { route ->
        if (route.showTopBar) {
            val parentRouteName: Int? = (route as? Route.NestedLevelRoute)?.parent?.displayName
            Box(
                modifier = modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = {
                        route.displayName?.let {
                            TextH3Bold(
                                textId = it,
                                color = white,
                                textAlign = TextAlign.Start
                            )
                        }
                    },
                    navigationIcon = {
                        if (parentRouteName != null) {
                            IconButton(
                                onClick = onBack,
                                modifier = Modifier.padding(horizontal = padding6)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = null,
                                    tint = white,
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().background(primary.copy(alpha = 0.6f)),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = white
                    )
                )
            }
        }
    }
}


@Composable
fun BottomBar(
    destinations: List<Route.TopLevelRoute>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (Route.TopLevelRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentRoute = Route.getRoute(currentDestination?.route)
    currentRoute?.let { route ->
        if (route.showBottomBar) {
            NavigationBar(containerColor = white) {
                destinations.forEach { destination ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val pressed by interactionSource.collectIsPressedAsState()
                    val selected =
                        currentDestination.isTopLevelDestinationInHierarchy(destination)

                    val selectedContentColor = if (pressed) black else primary
                    val unselectedContentColor = if (pressed) black else black

                    CompositionLocalProvider {
                        NavigationBarItem(
                            selected = selected,
                            onClick = { onNavigateToDestination(destination) },
                            icon = {
                                Icon(
                                    imageVector = destination.iconId,
                                    // painter = painterResource(id = destination.iconId),
                                    contentDescription = null,
                                    modifier = modifier.size(iconMediumSize)
                                )
                            },
                            label = {
                                TextBottomBar(
                                    textId = destination.titleTextId,
                                    color = Color.Unspecified
                                )
                            },
                            colors = NavigationBarItemDefaults.colors().copy(
                                selectedIconColor = selectedContentColor,
                                selectedTextColor = selectedContentColor,
                                selectedIndicatorColor = Color.Transparent,
                                unselectedIconColor = unselectedContentColor,
                                unselectedTextColor = unselectedContentColor,
                                disabledIconColor = white.copy(alpha = 0.3f),
                                disabledTextColor = white.copy(alpha = 0.3f),
                            ),
                            interactionSource = interactionSource,
                            modifier = modifier.background(
                                if (pressed) primary else Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(topLevelRoute: Route.TopLevelRoute) =
    this?.hierarchy?.any {
        it.route?.contains(topLevelRoute.name, true) ?: false
    } ?: false

@Composable
fun rememberAppUIState(
    navController: NavHostController = rememberNavController(),
): AppUIState {
    return remember(navController) {
        AppUIState(navController)
    }
}

@Stable
class AppUIState(
    val navController: NavHostController,
) {
    val topLevelDestinations: List<Route.TopLevelRoute> = Route.TopLevelRoute.entries
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        @Composable get() = Route.getRoute(currentDestination?.route)?.showBottomBar ?: true
}