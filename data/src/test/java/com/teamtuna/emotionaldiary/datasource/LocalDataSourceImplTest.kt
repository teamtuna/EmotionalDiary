package com.teamtuna.emotionaldiary.datasource

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.teamtuna.emotionaldiary.MainCoroutineRule
import com.teamtuna.emotionaldiary.db.EmotionRoomDatabase
import com.teamtuna.emotionaldiary.entity.Emotion
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

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
}
