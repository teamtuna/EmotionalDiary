package com.teamtuna.emotionaldiary.compose.calendar

import android.widget.ImageView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import be.sigmadelta.calpose.Calpose
import be.sigmadelta.calpose.WEIGHT_7DAY_WEEK
import be.sigmadelta.calpose.model.CalposeActions
import be.sigmadelta.calpose.model.CalposeDate
import be.sigmadelta.calpose.model.CalposeWidgets
import be.sigmadelta.calpose.widgets.DefaultDay
import be.sigmadelta.calpose.widgets.MaterialHeader
import com.bumptech.glide.Glide
import com.google.accompanist.insets.ProvideWindowInsets
import com.teamtuna.emotionaldiary.compose.theme.EmotionalDiaryTheme
import org.threeten.bp.DayOfWeek
import org.threeten.bp.YearMonth

@Preview
@Composable
fun CalendarComposeApp() {
    EmotionalDiaryTheme {
        ProvideWindowInsets {
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                MaterialPreview()
            }
        }
    }
}

@Preview("MaterialPreview")
@Composable
fun MaterialPreview() {
    var month by remember { mutableStateOf(YearMonth.now()) }
    var selectionSet by remember { mutableStateOf(setOf<CalposeDate>()) }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    MaterialCalendar(
        month = month,
        selectionSet = selectionSet,
        actions = CalposeActions(
            onClickedPreviousMonth = { month = month.minusMonths(1) },
            onClickedNextMonth = { month = month.plusMonths(1) },
        ),
        onSelected = {
            selectionSet = mutableSetOf(it).apply {
                setShowDialog(true)
                addAll(selectionSet)
            }
        }
    )

    EmotionDialog(showDialog, setShowDialog)
}

@Composable
fun MaterialCalendar(
    month: YearMonth,
    selectionSet: Set<CalposeDate>,
    actions: CalposeActions,
    onSelected: (CalposeDate) -> Unit,
) {
    Calpose(
        month = month,
        actions = actions,

        widgets = CalposeWidgets(
            header = { month, todayMonth, actions ->
                MaterialHeader(month, todayMonth, actions, Color(primaryAccent))
            },
            headerDayRow = { headerDayList -> HeaderDayRow(headerDayList = headerDayList) },
            day = { dayDate, todayDate ->
                Day(
                    dayDate = dayDate,
                    selectionSet = selectionSet,
                    onSelected = onSelected
                )
            },
            priorMonthDay = { dayDate -> PriorMonthDay(dayDate = dayDate) },
        )
    )
}

@Composable
fun HeaderDayRow(
    headerDayList: Set<DayOfWeek>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 16.dp),
    ) {
        headerDayList.forEach {
            DefaultDay(
                text = it.name.first().toString(),
                modifier = Modifier
                    .weight(WEIGHT_7DAY_WEEK)
                    .alpha(.6f),
                style = TextStyle(color = Color.Gray, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun RowScope.Day(
    dayDate: CalposeDate,
    selectionSet: Set<CalposeDate>,
    onSelected: (CalposeDate) -> Unit
) {
    val isSelected = selectionSet.contains(dayDate)
    val weight = WEIGHT_7DAY_WEEK
    val bgColor = if (isSelected) Color(primaryAccent) else Color.Transparent

    val widget: @Composable () -> Unit = {
        EmotionDay(
            text = dayDate.day.toString(),
            modifier = Modifier
                .padding(4.dp)
                .weight(weight)
                .fillMaxWidth(),
            style = TextStyle(
                color = when {
                    isSelected -> Color.White
                    else -> Color.Black
                },
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        )
    }

    Column(
        modifier = Modifier.weight(WEIGHT_7DAY_WEEK),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Crossfade(targetState = bgColor) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(4.dp)))
                    .clickable(onClick = { onSelected(dayDate) })
                    .background(it)
            ) {
                widget()
            }
        }
    }
}

@Composable
fun RowScope.PriorMonthDay(
    dayDate: CalposeDate
) {
    DefaultDay(
        text = dayDate.day.toString(),
        style = TextStyle(color = Color(lightGrey)),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .weight(WEIGHT_7DAY_WEEK)
    )
}

@Composable
fun EmotionDay(
    text: String,
    modifier: Modifier = Modifier.padding(4.dp),
    style: TextStyle = TextStyle()
) {
    val ctx = LocalContext.current
    val maxSize = 50.dp
    val url = "https://upload.wikimedia.org/wikipedia/commons/thumb/" +
        "e/e6/Noto_Emoji_KitKat_263a.svg/1200px-Noto_Emoji_KitKat_263a.svg.png"

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text,
            modifier = modifier,
            textAlign = TextAlign.Center,
            style = style
        )
        AndroidView(
            factory = {
                val img = ImageView(it)
                Glide.with(ctx)
                    .load(url)
                    .into(img)
                img
            },
            Modifier
                .weight(WEIGHT_7DAY_WEEK)
                .size(maxSize)
        )
    }
}

@Composable
fun EmotionDialog(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text("Title")
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                ) {
                    Text("Dismiss")
                }
            },
            text = {
                Text("This is a text on the dialog")
            },
        )
    }
}

const val lightGrey = 0xa0bdbdbd
const val primaryAccent = 0xFF4db6ac
const val primaryAccentLight = 0xFF82e9de
