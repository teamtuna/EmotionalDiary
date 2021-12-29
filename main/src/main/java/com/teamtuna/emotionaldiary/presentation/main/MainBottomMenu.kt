/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.teamtuna.emotionaldiary.presentation.main

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.teamtuna.emotionaldiary.main.R
import com.teamtuna.emotionaldiary.presentation.navigation.CALENDAR_ROUTE
import com.teamtuna.emotionaldiary.presentation.navigation.PREFERENCES_ROUTE
import com.teamtuna.emotionaldiary.presentation.navigation.STATISTICS_ROUTE
import com.teamtuna.emotionaldiary.presentation.navigation.TIMELINE_ROUTE

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    @StringRes val cescription: Int = 0, /*Resources.ID_NULL*/
) {
    object Calendar : Screen(CALENDAR_ROUTE, R.string.calendar, Icons.Filled.Favorite, R.string.calendar)
    object Favorite : Screen(TIMELINE_ROUTE, R.string.favorite, Icons.Filled.Favorite, R.string.favorite)
    object Statistics : Screen(STATISTICS_ROUTE, R.string.statistics, Icons.Filled.Favorite, R.string.statistics)
    object Preferences : Screen(PREFERENCES_ROUTE, R.string.preferences, Icons.Filled.Favorite, R.string.preferences)
}

@Composable
fun MainBottomMenu(bottomMenuNavHostController: NavHostController) {

    val items: List<Screen> = listOf(
        Screen.Calendar,
        Screen.Favorite,
        Screen.Statistics,
        Screen.Preferences,
    )
    BottomNavigation {
        val navBackStackEntry by bottomMenuNavHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = stringResource(id = screen.cescription)
                    )
                },
                label = { Text(text = stringResource(id = screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    bottomMenuNavHostController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(bottomMenuNavHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomMenu() {
    MainBottomMenu(rememberNavController())
}
