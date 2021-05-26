package com.teamtuna.emotionaldiary.di

import android.content.Context
import com.teamtuna.emotionaldiary.datasource.LocalDataSource
import com.teamtuna.emotionaldiary.datasource.LocalDataSourceImpl
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
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(
        @ApplicationContext context : Context  //need if use room lib
    ): LocalDataSource {
        return LocalDataSourceImpl()
    }
}