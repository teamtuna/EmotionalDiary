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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.teamtuna.emotionaldiary.presentation.main.MainScaffold
import com.teamtuna.emotionaldiary.presentation.write.WriteCompose

@Composable
fun MainNavGraph(
    mainNavHostController: NavHostController,
) {
    // val actions = remember(navController) { MainActions(navController) }
    // val coroutineScope = rememberCoroutineScope()
    // val homeNavigation = { homeNavigate(navController) }
    NavHost(
        navController = mainNavHostController,
        startDestination = MAIN_ROUTE,
    ) {
        composable(MAIN_ROUTE) {
            MainScaffold(mainNavHostController)
        }
        composable("$ARTICLE_ROUTE/{$ARTICLE_ID_KEY}") { backStackEntry ->
            WriteCompose(
                articleId = backStackEntry.arguments?.getString(ARTICLE_ID_KEY)?.toLong(),
                navigateUp = {
                    mainNavHostController.navigateUp()
                },
            )
        }
        composable(ARTICLE_ROUTE) {
            WriteCompose(
                navigateUp = {
                    mainNavHostController.navigateUp()
                    // navController.navigate(MAIN_ROUTE)
                },
            )
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
