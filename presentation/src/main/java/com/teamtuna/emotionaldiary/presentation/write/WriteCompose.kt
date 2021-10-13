package com.teamtuna.emotionaldiary.presentation.write

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamtuna.emotionaldiary.main.R
import com.teamtuna.emotionaldiary.presentation.theme.AppTheme

@Preview
@Composable
fun WriteCompose(
    articleId: Long? = null,
    navigateUp: () -> Unit = {},
    writeViewModel: WriteViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val uiState by writeViewModel.uiState.collectAsState()
    WriteScreen(
        uiState = uiState,
        scaffoldState = scaffoldState,
        onContentChanged = {
            writeViewModel.onContentChanged(it)
        },
        onConfirm = {
            writeViewModel.confirm()
        }
    )
}

@Composable
fun WriteScreen(
    uiState: WriteUiState,
    scaffoldState: ScaffoldState,
    onContentChanged: (String) -> Unit,
    onConfirm: () -> Unit
) {
    AppTheme {
        ProvideWindowInsets {
            val scrollState = rememberLazyListState()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    InsetAwareTopAppBar(
                        title = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    style = MaterialTheme.typography.subtitle2,
                                    color = LocalContentColor.current,
                                    modifier = Modifier
                                        .weight(1.5f)
                                )
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = onConfirm) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        },
                        elevation = if (!scrollState.isScrollInProgress) 0.dp else 4.dp,
                        backgroundColor = MaterialTheme.colors.surface
                    )
                }
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                        .fillMaxHeight(),
                    state = scrollState
                ) {
                    item {
                        ImageCard(uiState)
                    }
                    item {
                        WriteTextField(
                            modifier = Modifier.padding(18.dp),
                            contentChanged = onContentChanged
                        )
                    }
                }
            }
        }
    }
}
