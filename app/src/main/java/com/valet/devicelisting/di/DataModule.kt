package com.valet.devicelisting.di

import android.content.Context
import com.valet.devicelisting.domain.DataRepository
import com.valet.devicelisting.domain.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepositoryRepository(dataRepository: DataRepository): IDataRepository

}