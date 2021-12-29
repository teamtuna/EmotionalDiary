package com.teamtuna.emotionaldiary.presentation.calendar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import be.sigmadelta.calpose.Calpose
import be.sigmadelta.calpose.WEIGHT_7DAY_WEEK
import be.sigmadelta.calpose.model.CalposeActions
import be.sigmadelta.calpose.model.CalposeDate
import be.sigmadelta.calpose.model.CalposeWidgets
import be.sigmadelta.calpose.widgets.DefaultDay
import be.sigmadelta.calpose.widgets.MaterialHeader
import com.teamtuna.emotionaldiary.domain.entity.DailyEmotion
import com.teamtuna.emotionaldiary.domain.entity.UniqId
import org.threeten.bp.DayOfWeek
import org.threeten.bp.YearMonth

typealias WriteNavigation = (UniqId) -> Unit

@Composable
fun CalendarComposeApp(writeNavigation: WriteNavigation) {
    MaterialPreview(writeNavigation)
}

@Composable
fun MaterialPreview(writeNavigation: WriteNavigation) {
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
            val dailyEmotion: DailyEmotion = DailyEmotion.findDailyEmotionIDByDate(calposeDate)
            writeNavigation(dailyEmotion.id)
            // newIntent 작업한 곳으로 보내기
            // 날짜, emotion Id 값이 보내져아함
        }
    )
}

private fun DailyEmotion.Companion.findDailyEmotionIDByDate(calposeDate: CalposeDate): DailyEmotion {
    return DailyEmotion.EMPTY.copy()
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

const val lightGrey = 0xa0bdbdbd
const val todayColor = 0xFF30cf5a
const val primaryAccent = 0xFF4db6ac
const val primaryAccentLight = 0xFF82e9de
