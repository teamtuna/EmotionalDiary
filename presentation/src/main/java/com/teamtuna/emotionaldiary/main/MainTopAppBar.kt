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

package com.teamtuna.emotionaldiary.main

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable

@Composable
fun MainTopAppBar(
    title: String,
    homeNavigate: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        // https://android-review.googlesource.com/c/platform/frameworks/support/+/1235108/4/ui/ui-material/integration-tests/samples/src/main/java/androidx/ui/material/samples/AppBarSamples.kt#39
        navigationIcon = {
            IconButton(onClick = { homeNavigate() }) {
                Icon(
                    Icons.Filled.PlusOne,
                    contentDescription = title
                )
            }
        },
    )
}
