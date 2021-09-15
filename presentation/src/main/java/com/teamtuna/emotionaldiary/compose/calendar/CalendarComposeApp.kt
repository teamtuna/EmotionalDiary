package com.teamtuna.emotionaldiary.compose.calendar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import be.sigmadelta.calpose.Calpose
import be.sigmadelta.calpose.WEIGHT_7DAY_WEEK
import be.sigmadelta.calpose.model.CalposeActions
import be.sigmadelta.calpose.model.CalposeDate
import be.sigmadelta.calpose.model.CalposeWidgets
import be.sigmadelta.calpose.widgets.DefaultDay
import be.sigmadelta.calpose.widgets.MaterialHeader
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.teamtuna.emotionaldiary.compose.theme.EmotionalDiaryTheme
import org.threeten.bp.DayOfWeek
import org.threeten.bp.YearMonth

@Preview
@Composable
fun CalendarComposeApp() {
    val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)

    EmotionalDiaryTheme {
        ProvideWindowInsets {
            Column {
                Spacer(
                    modifier = Modifier
                        .background(appBarColor)
                        .fillMaxWidth()
                        .statusBarsHeight()
                )
                MaterialPreview()
            }
        }
    }
}

@Composable
fun MaterialPreview() {
    var month by remember { mutableStateOf(YearMonth.now()) }
    var selectionSet by remember { mutableStateOf(setOf<CalposeDate>()) }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    var currentDate by remember {
        mutableStateOf(CalposeDate(-1, DayOfWeek.MONDAY, YearMonth.now()))
    }

    MaterialCalendar(
        month = month,
        selectionSet = selectionSet,
        actions = CalposeActions(
            onClickedPreviousMonth = { month = month.minusMonths(1) },
            onClickedNextMonth = { month = month.plusMonths(1) },
        ),
        onSelected = { calPoseDate ->
            selectionSet = mutableSetOf(calPoseDate).apply {
                currentDate = calPoseDate
                setShowDialog(true)
                addAll(selectionSet)
            }
        }
    )

    EmotionListDialog(
        showDialog,
        setShowDialog,
        currentDate,
        onDismiss = {
            // dialog 없어진 후 작업이 필요할 경
        },
        onEmotionClick = { calposeDate, emotionId ->
            // newIntent 작업한 곳으로 보내기
            // 날짜, emotion Id 값이 보내져아함
        }
    )
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
                    todayDate = todayDate,
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
    todayDate: CalposeDate,
    selectionSet: Set<CalposeDate>,
    onSelected: (CalposeDate) -> Unit
) {
    val isToday = todayDate == dayDate
    val isSelected = selectionSet.contains(dayDate)
    val bgColor = when {
        isSelected -> Color(primaryAccent)
        isToday -> Color(todayColor)
        else -> Color.Transparent
    }

    val widget: @Composable () -> Unit = {
        EmotionDay(
            text = dayDate.day.toString(),
            style = TextStyle(
                color = when {
                    isSelected || isToday -> Color.White
                    else -> Color.Black
                },
                fontWeight = if (isSelected || isToday) FontWeight.Bold else FontWeight.Normal
            )
        )
    }

    Column(
        modifier = Modifier
            .weight(WEIGHT_7DAY_WEEK)
            .padding(4.dp),
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
    style: TextStyle = TextStyle()
) {
    val url = "https://upload.wikimedia.org/wikipedia/commons/thumb/" +
        "e/e6/Noto_Emoji_KitKat_263a.svg/1200px-Noto_Emoji_KitKat_263a.svg.png"

    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text,
            Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = style
        )
        Image(
            painter = rememberImagePainter(
                data = url,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@Composable
private fun EmotionListDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    currentDate: CalposeDate,
    onDismiss: () -> Unit,
    onEmotionClick: (CalposeDate, Int) -> Unit,
) {
    val url = "https://upload.wikimedia.org/wikipedia/commons/thumb/" +
        "e/e6/Noto_Emoji_KitKat_263a.svg/1200px-Noto_Emoji_KitKat_263a.svg.png"

    var emotion by remember { mutableStateOf(-1) }

    if (!showDialog || currentDate.day == -1)
        return

    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = "Select Emotion",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // TODO  정의된 emotion 개수에 맞게 적절하게 배치되도록 수정하기 (dialog height, emotion item size)
                    for (i in 0 until 2) {
                        Row(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            for (j in 0 until 4) {
                                Box(
                                    Modifier.clickable(
                                        onClick = {
                                            onEmotionClick(currentDate, emotion)
                                            setShowDialog(false)
                                        }
                                    )
                                ) {
                                    Image(
                                        painter = rememberImagePainter(
                                            data = url,
                                            builder = {
                                                transformations(CircleCropTransformation())
                                            }
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(50.dp)
                                            .height(50.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

const val lightGrey = 0xa0bdbdbd
const val todayColor = 0xFF30cf5a
const val primaryAccent = 0xFF4db6ac
const val primaryAccentLight = 0xFF82e9de
