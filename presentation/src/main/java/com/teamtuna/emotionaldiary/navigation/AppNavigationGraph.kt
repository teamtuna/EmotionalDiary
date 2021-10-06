package com.teamtuna.emotionaldiary.navigation

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.teamtuna.emotionaldiary.compose.calendar.MaterialPreview
import com.teamtuna.emotionaldiary.main.BottomMenu
import com.teamtuna.emotionaldiary.setting.SettingList
import com.teamtuna.emotionaldiary.timeline.TimeLineContent
import com.teamtuna.emotionaldiary.write.WriteActivity

/**
https://developer.android.com/jetpack/compose/navigation

// Pop everything up to the "home" destination off the back stack before
// navigating to the "friends" destination
navController.navigate(“friends”) {
popUpTo("home")
}

// Pop everything up to and including the "home" destination off
// the back stack before navigating to the "friends" destination
navController.navigate("friends") {
popUpTo("home") { inclusive = true }
}

// Navigate to the "search” destination only if we’re not already on
// the "search" destination, avoiding multiple copies on the top of the
// back stack
navController.navigate("search") {
launchSingleTop = true
}

navController.navigate("profile/user1234")

// https://developer.android.com/guide/navigation/navigation-pass-data#supported_argument_types

 */

@Composable
fun AppNavigationGraph(navController: NavHostController) {
    return NavHost(
        navController = navController,
        startDestination = BottomMenu.CALENDAR.name
        // startDestination = Screen.Calendar.route + "/{date}"
    ) {
        // composable(Screen.Calendar.route + "/{date}") {
        //     val arguments = listOf(navArgument("date") {
        //         type = NavType.StringType
        //     })
        //     MaterialPreview(navController, arguments)
        // }
        // composable(Screen.Timeline.route + "/{date}") { backStackEntry ->
        //     TimeLineContent(navController, backStackEntry.arguments?.getString("date"))
        // }
        // composable(Screen.Analysis.route + "/{from}" + "/{to}") {

        composable(BottomMenu.CALENDAR.name) {
            MaterialPreview()
        }
        composable(BottomMenu.TIMELINE.name) {
            TimeLineContent()
        }
        composable(BottomMenu.ANALYSIS.name) {
            Surface {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    text = BottomMenu.ANALYSIS.name,
                )
            }
        }
        composable(BottomMenu.SETTING.name) {
            // SettingList()
            Surface {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),

                    text = BottomMenu.SETTING.name,
                )
            }
        }
        composable("write") {
            navController.context.startActivity(
                Intent(
                    navController.context,
                    WriteActivity::class.java
                )
            )
        }
    }
}
