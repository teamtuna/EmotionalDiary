package com.teamtuna.emotionaldiary.data.datasource

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.teamtuna.emotionaldiary.data.MainCoroutineRule
import com.teamtuna.emotionaldiary.data.db.EmotionRoomDatabase
import com.teamtuna.emotionaldiary.domain.entity.DailyEmotion
import com.teamtuna.emotionaldiary.domain.entity.Emotion
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.LocalDateTime

@Config(sdk = [30])
@RunWith(RobolectricTestRunner::class)
class LocalDataSourceImplTest {

    private lateinit var emotionRoomDb: EmotionRoomDatabase
    private lateinit var localDataSource: LocalDataSource

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        emotionRoomDb =
            Room.inMemoryDatabaseBuilder(getApplicationContext(), EmotionRoomDatabase::class.java)
                .build()
        localDataSource = LocalDataSourceImpl(emotionRoomDb.fcmDao())
    }

    @After
    fun cleanUp() {
        emotionRoomDb.close()
    }

    @Test
    fun insertTask() = runBlocking {
        val reason = "test 내가 만든것도 아닌데 혼남"
        val dbId = localDataSource.add(Emotion.FEAR, reason)
        assert(dbId > 0)
    }

    @Test
    fun `기존_데이터가_없으면_어떻게_하지`() = runBlocking {
        val emotionalEntity = localDataSource.get(1)
        assertNull(emotionalEntity)
    }

    @Test
    fun `기존_데이터가_있으면_원하는_데이터를_줍시다`() = runBlocking {
        val reason = "test 내가 만든것도 아닌데 혼남"
        val dbId = localDataSource.add(Emotion.FEAR, reason)
        val entity = requireNotNull(localDataSource.get(dbId))

        assertEquals(Emotion.FEAR, entity.emotion)
        assertEquals(reason, entity.reason)
    }

    @Test
    fun `replace 기존 데이터가 존재하면 데이터를 바꿔서 저장한다`() = runBlocking {
        val actualReason = "test2"
        val currentDate = LocalDateTime.now()
        assertTrue(
            localDataSource.replace(
                DailyEmotion(1L, Emotion.JOY, currentDate, null, null, actualReason)
            )
        )

        val entity = requireNotNull(localDataSource.get(1L))

        assertEquals(Emotion.JOY, entity.emotion)
        assertEquals(actualReason, entity.reason)
    }

    @Test
    fun `replace 기존 데이터가 존재하지 않으면 새롭게 데이터를 넣어서 저장한다`() = runBlocking {
        val reason = "test1"
        val actualReason = "test2"
        val currentDate = LocalDateTime.now()
        val dbId = localDataSource.add(Emotion.FEAR, reason)
        assertTrue(
            localDataSource.replace(
                DailyEmotion(dbId, Emotion.JOY, currentDate, null, null, actualReason)
            )
        )

        val entity = requireNotNull(localDataSource.get(dbId))

        assertEquals(Emotion.JOY, entity.emotion)
        assertEquals(actualReason, entity.reason)
    }

    @Test
    fun `delete 기존 데이터가 존재하면 데이터를 삭제하도록 한다`() = runBlocking {
        val reason = "existData"
        val dbId = localDataSource.add(Emotion.FEAR, reason)

        localDataSource.delete(dbId)

        val existEmotional = localDataSource.get(dbId)

        assertNull(existEmotional)
    }

    @Test
    fun `delete 기존 데이터가 존재하지 않으면 데이터 삭제를 해도 아무런 동작을 하지 않는다`() = runBlocking {
        localDataSource.delete(1L)

        val existEmotional = localDataSource.get(1L)

        assertNull(existEmotional)
    }
}
