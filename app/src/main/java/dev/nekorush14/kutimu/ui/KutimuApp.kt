package dev.nekorush14.kutimu.ui

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.nekorush14.kutimu.R
import dev.nekorush14.kutimu.ui.screen.HomeScreen
import dev.nekorush14.kutimu.ui.theme.KutimuTheme
import dev.nekorush14.kutimu.ui.viewmodel.KutimuViewModel

const val HOME_ROUTE = "home"
const val ALL_HABITS_ROUTE = "all_habits"
const val ALL_TASKS_ROUTE = "all_tasks"

/**
 * All screens in the app.
 *
 * @param route Route for the screen.
 * @param title Title for the screen.
 * @param icon Icon for the screen.
 * @param selectedIcon Selected icon for the screen.
 */
sealed class KutimuAppScreen(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
) {
    data object Home : KutimuAppScreen(
        route = HOME_ROUTE,
        title = R.string.screen_home,
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
    )

    data object AllHabits : KutimuAppScreen(
        route = ALL_HABITS_ROUTE,
        title = R.string.screen_all_habits,
        icon = Icons.Outlined.Repeat,
        selectedIcon = Icons.Filled.Repeat,
    )

    data object AllTasks : KutimuAppScreen(
        route = ALL_TASKS_ROUTE,
        title = R.string.screen_all_tasks,
        icon = Icons.AutoMirrored.Outlined.List,
        selectedIcon = Icons.AutoMirrored.Filled.List,
    )
}

/**
 * App main composable.
 *
 * [KutimuApp] is the main composable for the Kutimu app.
 * The app is divided into three screens: [KutimuAppScreen.Home], [KutimuAppScreen.AllHabits],
 * and [KutimuAppScreen.AllTasks].
 * Each screen can navigate to the other screens using the [KutimuBottomNavigationBar].
 *
 * @param viewModel A [ViewModel] for the app.
 * @param navController NavHostController for the app.
 * @param onThemeIconClick Callback for when the theme icon is clicked.
 * @param isDarkMode Whether the app is in dark mode.
 */
@Composable
fun KutimuApp(
    viewModel: KutimuViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    onThemeIconClick: () -> Unit = {},
    isDarkMode: Boolean = false,
) {
    val homeScreen = KutimuAppScreen.Home.route
    val allHabitsScreen = KutimuAppScreen.AllHabits.route
    val allTasksScreen = KutimuAppScreen.AllTasks.route
    val currentRoute = currentRoute(navController)

    Scaffold(
        topBar = {
            KutimuTopAppBar(
                isDarkMode = isDarkMode,
                onThemeIconClick = onThemeIconClick,
            )
        },
        bottomBar = {
            KutimuBottomNavigationBar(
                navController = navController,
                onBottomNavIconClick = {
                    if (currentRoute == KutimuAppScreen.Home.route) {
                        viewModel.updateGreetingMessage()
                    }
                },
                items = listOf(
                    KutimuAppScreen.Home,
                    KutimuAppScreen.AllHabits,
                    KutimuAppScreen.AllTasks,
                )
            )
        },
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = KutimuAppScreen.Home.route,
            modifier = Modifier
                .padding(innerPadding),
        ) {
            // Bottom navigation screens
            composable(route = homeScreen) {
                HomeScreen(
                    habits = uiState.habits,
                    tasks = uiState.tasks,
                    greetingMessage = stringResource(uiState.greetingMessage),
                )
            }
            composable(route = allHabitsScreen) {
                // AllHabitsScreen()
            }
            composable(route = allTasksScreen) {
                // AllTasksScreen()
            }
        }
    }
}

/**
 * Top app bar for the Kutimu app.
 *
 * @param isDarkMode Whether the app is in dark mode.
 * @param onThemeIconClick Callback for when the theme icon is clicked.
 * @param modifier Modifier to be applied to the top app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KutimuTopAppBar(
    isDarkMode: Boolean = false,
    onThemeIconClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
            )
        },
        modifier = modifier,
        actions = {
            if (isDarkMode) {
                IconButton(
                    content = {
                        Icon(
                            imageVector = Icons.Outlined.LightMode,
                            contentDescription = stringResource(R.string.description_dark_mode)
                        )
                    },
                    onClick = onThemeIconClick
                )
            } else {
                IconButton(
                    content = {
                        Icon(
                            imageVector = Icons.Outlined.ModeNight,
                            contentDescription = stringResource(R.string.description_light_mode)
                        )
                    },
                    onClick = onThemeIconClick
                )
            }
        }
    )
}

@Composable
fun KutimuBottomNavigationBar(
    navController: NavHostController,
    items: List<KutimuAppScreen>,
    onBottomNavIconClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        val currentRoute = currentRoute(navController)

        items.forEach { screen ->
            val isSelected = currentRoute == screen.route
            val icon = if (isSelected) screen.selectedIcon else screen.icon

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(screen.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.title)
                    )
                },
                selected = isSelected,
                onClick = {
                    // bottom nav custom logic
                    onBottomNavIconClick()

                    // navigate to the screen
                    navController.navigate(screen.route) {
                        // Pop up to the root destination of the graph to
                        // avoid duplicate screen instances
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}

/**
 * Returns the current route as a string.
 */
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun KutimuTopAppBarPreview() {
    val configuration = LocalConfiguration.current
    val isDarkMode =
        configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    KutimuTheme {
        KutimuTopAppBar(
            isDarkMode = isDarkMode,
            onThemeIconClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun KutimuAppScreenPreview() {
    val configuration = LocalConfiguration.current
    val isDarkMode =
        configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    KutimuTheme {
        KutimuApp(
            isDarkMode = isDarkMode,
            onThemeIconClick = {}
        )
    }
}