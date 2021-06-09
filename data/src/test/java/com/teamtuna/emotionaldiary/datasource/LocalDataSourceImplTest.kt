package com.teamtuna.emotionaldiary.datasource

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.teamtuna.emotionaldiary.CoroutineTest
import com.teamtuna.emotionaldiary.MainCoroutineRule
import com.teamtuna.emotionaldiary.db.EmotionRoomDatabase
import com.teamtuna.emotionaldiary.entity.Emotion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LocalDataSourceImplTest : CoroutineTest() {

    private lateinit var emotionRoomDb: EmotionRoomDatabase
    private lateinit var localDataSource: LocalDataSource

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
    fun insertTask() = runWorkerTest(Dispatchers.IO) {
        val reason = "test 내가 만든것도 아닌데 혼남"
        val dbId = localDataSource.add(Emotion.FEAR, reason)
        assert(dbId > 0)
    }

}