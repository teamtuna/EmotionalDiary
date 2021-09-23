package com.teamtuna.emotionaldiary.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.teamtuna.emotionaldiary.presentation.R

@Composable
fun MainScaffold(
    navController: NavHostController = rememberNavController(),
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = { MainTopAppBar(stringResource(R.string.app_name)) },
        bottomBar = { MainBottomMenu(navController) },
    ) {
        Box(modifier = Modifier.padding(it)) {
            content(it)
        }
    }
}
