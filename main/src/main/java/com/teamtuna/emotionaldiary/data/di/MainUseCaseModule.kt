package com.teamtuna.emotionaldiary.data.di

import android.content.Context
import androidx.room.Room
import com.teamtuna.emotionaldiary.data.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.data.datasource.LocalDataSourceImpl
import com.teamtuna.emotionaldiary.data.db.EmotionRoomDatabase
import com.teamtuna.emotionaldiary.data.repository.EmotionRepositoryImpl
import com.teamtuna.emotionaldiary.domain.repository.EmotionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EmotionRepositoryModule {
    @Singleton
    @Provides
    fun provideEmotionRepository(localDataSource: LocalDataSource): EmotionRepository {
        return EmotionRepositoryImpl(localDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(
        database: EmotionRoomDatabase // need if use room lib
    ): LocalDataSource {
        return LocalDataSourceImpl(database.fcmDao())
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): EmotionRoomDatabase {
        return Room.databaseBuilder(
            context,
            EmotionRoomDatabase::class.java, "emotion-db"
        ).build()
    }
}
