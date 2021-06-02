package com.teamtuna.emotionaldiary.di

import android.content.Context
import androidx.room.Room
import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.datasource.LocalDataSourceImpl
import com.teamtuna.emotionaldiary.db.EmotionRoomDatabase
import com.teamtuna.emotionaldiary.repository.EmotionRepository
import com.teamtuna.emotionaldiary.repository.EmotionRepositoryImpl
import com.teamtuna.emotionaldiary.repository.MainRepository
import com.teamtuna.emotionaldiary.repository.MainRepositoryImpl
import com.teamtuna.emotionaldiary.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object MainUseCaseModule {

    @Provides
    fun provideMainUseCase(repository: MainRepository): MainUseCase {
        return MainUseCase(repository)
    }
}


@Module
@InstallIn(SingletonComponent::class)
object MainRepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(localDataSource: LocalDataSource): MainRepository {
        return MainRepositoryImpl(localDataSource)
    }
}

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
        @ApplicationContext context : Context  //need if use room lib
    ): LocalDataSource {
        return LocalDataSourceImpl()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context : Context
    ): EmotionRoomDatabase {
        return Room.databaseBuilder(
            context,
            EmotionRoomDatabase::class.java, "emotion-db"
        ).build()
    }
}