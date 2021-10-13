package com.teamtuna.emotionaldiary.presentation.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SettingList(modifier: Modifier = Modifier) {
    var selected by remember { mutableStateOf("앱정보") }

    val settingItemList = listOf<String>("앱정보", "서비스 이용약관", "오픈소스 라이선스", "로그아웃", "탈퇴")
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(settingItemList) {
            SettingItem(
                name = it, selected == it,
                Modifier.clickable(onClick = {
                    selected = it
                })
            )
        }
    }
}

@Composable
private fun SettingItem(
    name: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Text(text = name, modifier = modifier, color = if (isSelected) Color.Red else Color.Blue)
}
