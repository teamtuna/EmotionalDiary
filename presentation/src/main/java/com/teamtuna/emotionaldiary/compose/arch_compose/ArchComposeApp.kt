package com.teamtuna.emotionaldiary.compose.arch_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.teamtuna.emotionaldiary.theme.AppTheme

@Preview
@Composable
fun ArchComposeApp(viewModel: ArchComposeViewModel = viewModel()) {
    AppTheme {
        ProvideWindowInsets {
            val testData = viewModel.getTestLiveData().observeAsState()
            // var testFlowData by remember { viewModel.getTestFlow().collectAsState(initial = "initial data") }
            var name1 by remember { mutableStateOf("") }
            var name2 = viewModel.getTestFlow().collectAsState(initial = "initial data")
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color(0x55ff0000))
            }
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                testData.value?.let {
                    Text(text = it)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = name2.value)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = name1,
                    onValueChange = { name1 = it },
                    label = { Text("Name") }
                )
            }
        }
    }
}
