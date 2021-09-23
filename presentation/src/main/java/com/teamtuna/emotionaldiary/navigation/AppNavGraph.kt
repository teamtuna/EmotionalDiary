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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teamtuna.emotionaldiary.compose.calendar.MaterialPreview
import com.teamtuna.emotionaldiary.main.MainScaffold
import com.teamtuna.emotionaldiary.navigation.MainDestinations.ARTICLE_ID_KEY
import com.teamtuna.emotionaldiary.navigation.MainDestinations.ARTICLE_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.CALENDAR_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.PREFERENCES_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.STATISTICS_ROUTE
import com.teamtuna.emotionaldiary.navigation.MainDestinations.TIMELINE_ROUTE
import com.teamtuna.emotionaldiary.preferences.PreferencesScreen
import com.teamtuna.emotionaldiary.statistics.StatisticsScreen
import com.teamtuna.emotionaldiary.timeline.TimeLineContent

object MainDestinations {
    const val CALENDAR_ROUTE = "calendar"
    const val TIMELINE_ROUTE = "timeline"
    const val STATISTICS_ROUTE = "statistics"
    const val PREFERENCES_ROUTE = "preferences"
    const val ARTICLE_ROUTE = "article"
    const val ARTICLE_ID_KEY = "articleId"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = CALENDAR_ROUTE,
) {
    val actions = remember(navController) { MainActions(navController) }
    val coroutineScope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(CALENDAR_ROUTE) {
            MainScaffold(navController) {
                MaterialPreview(
                    // navigateToArticle = actions.navigateToArticle,
                )
            }
        }
        composable(TIMELINE_ROUTE) {
            MainScaffold(navController) {
                TimeLineContent(
                    // navigateToArticle = actions.navigateToArticle,
                )
            }
        }
        composable(STATISTICS_ROUTE) {
            MainScaffold(navController) {
                StatisticsScreen(
                )
            }
        }
        composable(PREFERENCES_ROUTE) {
            MainScaffold(navController) {
                PreferencesScreen(
                )
            }
        }
        composable("$ARTICLE_ROUTE/{$ARTICLE_ID_KEY}") { backStackEntry ->
            // WriteScreen(                )
        }
        composable("$ARTICLE_ROUTE/{$ARTICLE_ID_KEY}") { backStackEntry ->
            // ArticleScreen(
            //     postId = backStackEntry.arguments?.getString(ARTICLE_ID_KEY),
            //     onBack = actions.upPress,
            // )
        }
    }
    // }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val navigateToArticle: (String) -> Unit = { postId: String ->
        navController.navigate("$ARTICLE_ROUTE/$postId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
