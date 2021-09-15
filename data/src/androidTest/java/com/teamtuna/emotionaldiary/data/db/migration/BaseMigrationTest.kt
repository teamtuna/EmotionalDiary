package com.teamtuna.emotionaldiary.data.db.migration

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.teamtuna.emotionaldiary.db.EmotionRoomDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseMigrationTest {

    companion object {
        const val TEST_DB_NAME = "migration-test"
    }

    @get:Rule
    val migrationHelper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        EmotionRoomDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Before
    fun before() {
        clearDbFile()
    }

    @After
    fun after() {
        clearDbFile()
    }

    private fun clearDbFile() {
        val dbFile =
            InstrumentationRegistry.getInstrumentation().context.getDatabasePath(TEST_DB_NAME)
        if (dbFile.exists() && dbFile.length() > 0) {
            dbFile.delete()
        }
    }
}
