package com.task.products.di

import com.task.products.data.remote.microservices.products.ProductApi
import com.task.products.data.remote.microservices.products.ProductRepositoryRemote
import com.task.products.domain.DataRepository
import com.task.products.domain.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepositoryRepository(dataRepository: DataRepository): IDataRepository

    @Binds
    @Singleton
    abstract fun provideMessagesRepository(productRepositoryRemote: ProductRepositoryRemote): ProductApi
}