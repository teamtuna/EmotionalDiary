package com.teamtuna.emotionaldiary.data.db.migration

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.teamtuna.emotionaldiary.db.migration.Migration1To2
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Migration1To2Test : BaseMigrationTest() {

    @Test
    fun migration_test() {
        migrationHelper.createDatabase(TEST_DB_NAME, 1).apply {
            insert(
                "EmotionalEntity", SQLiteDatabase.CONFLICT_FAIL,
                ContentValues().apply {
                    put("emotion", "JOY")
                    put("date", 0L)
                    put("reason", "TEST")
                }
            )
        }

        migrationHelper.runMigrationsAndValidate(
            TEST_DB_NAME,
            2,
            true,
            Migration1To2()
        ).apply {
            query("SELECT * FROM EmotionalEntity").run {
                MatcherAssert.assertThat(count, `is`(1))
                close()
            }
            close()
        }
    }
}
