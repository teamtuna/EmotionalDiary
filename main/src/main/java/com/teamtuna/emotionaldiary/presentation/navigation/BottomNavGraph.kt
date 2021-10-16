/*
 * Copyright 2021 The Android Open Source Project
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

package com.teamtuna.emotionaldiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.teamtuna.emotionaldiary.presentation.calendar.CalendarComposeApp
import com.teamtuna.emotionaldiary.presentation.setting.SettingList
import com.teamtuna.emotionaldiary.presentation.statistics.StatisticsScreen
import com.teamtuna.emotionaldiary.presentation.timeline.TimeLineContent

@Composable
fun BottomNavGraph(
    mainNavHostController: NavHostController,
    bottomNavHostController: NavHostController,
    modifier: Modifier,
) {
    // val actions = remember(navController) { MainActions(navController) }
    // val coroutineScope = rememberCoroutineScope()
    // val homeNavigation = { homeNavigate(navController) }
    NavHost(
        modifier = modifier,
        navController = bottomNavHostController,
        startDestination = CALENDAR_ROUTE,
    ) {
        composable(CALENDAR_ROUTE) {
            CalendarComposeApp {
                mainNavHostController.navigate("$ARTICLE_ROUTE/$it") {
                    popUpTo(mainNavHostController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
        composable(TIMELINE_ROUTE) {
            TimeLineContent { }
        }
        composable(STATISTICS_ROUTE) {
            StatisticsScreen()
        }
        composable(PREFERENCES_ROUTE) {
            SettingList()
        }
    }
}
