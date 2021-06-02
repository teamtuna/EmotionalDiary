package com.teamtuna.emotionaldiary.datasource

import androidx.room.Room
import com.teamtuna.emotionaldiary.entity.Emotion
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.teamtuna.emotionaldiary.MainCoroutineRule
import com.teamtuna.emotionaldiary.db.EmotionRoomDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        emotionRoomDb = Room.inMemoryDatabaseBuilder(getApplicationContext(), EmotionRoomDatabase::class.java).build()
        localDataSource = LocalDataSourceImpl(emotionRoomDb.fcmDao())
    }

    @After
    fun cleanUp() {
        emotionRoomDb.close()
    }

    @Test
    fun insertTask() = runBlockingTest {

        val reason = "test 내가 만든것도 아닌데 혼남"
        val dbId = localDataSource.add(Emotion.FEAR, reason)

        assert(dbId > 0)
    }
}