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

package com.teamtuna.emotionaldiary.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.teamtuna.emotionaldiary.compose.calendar.CalendarComposeApp
import com.teamtuna.emotionaldiary.main.MainScaffold
import com.teamtuna.emotionaldiary.navigation.MainDestinations.ARTICLE_ID_KEY
import com.teamtuna.emotionaldiary.navigation.MainDestinations.ARTICLE_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.CALENDAR_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.PREFERENCES_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.ROOT_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.STATISTICS_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.TIMELINE_ROUTE
import com.teamtuna.emotionaldiary.setting.SettingList
import com.teamtuna.emotionaldiary.statistics.StatisticsScreen
import com.teamtuna.emotionaldiary.timeline.TimeLineContent
import com.teamtuna.emotionaldiary.write.WriteCompose

object MainDestinations {
    const val ROOT_ROUTE = "root"
    const val CALENDAR_ROUTE = "calendar"
    const val TIMELINE_ROUTE = "timeline"
    const val STATISTICS_ROUTE = "statistics"
    const val PREFERENCES_ROUTE = "preferences"
    const val ARTICLE_ROUTE = "article"
    const val ARTICLE_ID_KEY = "articleId"
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    // val actions = remember(navController) { MainActions(navController) }
    // val coroutineScope = rememberCoroutineScope()
    // val homeNavigation = { homeNavigate(navController) }
    NavHost(
        navController = navController,
        startDestination = ROOT_ROUTE,
    ) {
        navigation(CALENDAR_ROUTE, route = ROOT_ROUTE) {
            composable(CALENDAR_ROUTE) {
                MainScaffold(navController) {
                    CalendarComposeApp {
                        navController.navigate("$ARTICLE_ROUTE/$it") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
            composable(TIMELINE_ROUTE) {
                MainScaffold(navController) {
                    TimeLineContent {
                    }
                }
            }
            composable(STATISTICS_ROUTE) {
                MainScaffold(navController) {
                    StatisticsScreen()
                }
            }
            composable(PREFERENCES_ROUTE) {
                MainScaffold(navController) {
                    SettingList()
                }
            }
            composable("$ARTICLE_ROUTE/{$ARTICLE_ID_KEY}") { backStackEntry ->
                WriteCompose(
                    articleId = backStackEntry.arguments?.getString(ARTICLE_ID_KEY)?.toLong(),
                    navigateUp = {
                        navController.navigateUp()
                    },
                )
            }
            composable(ARTICLE_ROUTE) {
                WriteCompose(
                    navigateUp = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}

fun writeNavigate(navController: NavHostController) {
    navController.navigate(ARTICLE_ROUTE) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

/**
 * Models the navigation actions in the app.
 */
// class MainActions(navController: NavHostController) {
//     val navigateToArticle: (String) -> Unit = { postId: String ->
//         navController.navigate("$ARTICLE_ROUTE/$postId")
//     }
//     val upPress: () -> Unit = {
//         navController.navigateUp()
//     }
// }
