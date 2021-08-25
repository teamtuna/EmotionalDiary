package com.teamtuna.emotionaldiary.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(
        color = Color.Green,
        border = BorderStroke(3.dp, Color.Blue)
    ) {
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
       Text(text = "current count : $count!")
    }
}

@Composable
fun CardView(
    onClick: () -> Unit
) {
    val padding = 16.dp
    Column(
        modifier = Modifier
            .padding(padding)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "",
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Red, CircleShape)
            )
            Spacer(Modifier.size(padding))
            Column {
                Text("Duzizzz", modifier = Modifier.fillMaxWidth())
                Text("What???!!!!!! Hi there.")
            }
        }
        Spacer(Modifier.size(padding))
        Card(elevation = 4.dp) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {}
        }
    }
}

@Preview(showBackground = true, name = "Text preview")
@Composable
fun MainContent(names: List<String> = listOf("AndroidAndroidAndroidAndroidAndroid", "there")) {
    BasicsCodeLabTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                for (i in names.indices) {
                    if (i != names.size) {
                        Greeting(names[i])
                        Divider(
                            color = Color.Black,
                            modifier = Modifier.padding(
                                top = 24.dp,
                                bottom = 24.dp
                            )
                        )
                    } else {
                        Greeting(names[i])
                    }
                }
                Spacer(Modifier.size(30.dp))

                val count = remember { mutableStateOf(0) }
                Counter(
                    count = count.value,
                    updateCount = { newCount ->
                        count.value = newCount
                    }
                )

                Spacer(
                    Modifier
                        .size(30.dp)
                        .fillMaxWidth()
                )

                CardView {

                }
            }
        }
    }
}
