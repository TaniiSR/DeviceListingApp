package com.task.products.di

import com.task.products.data.remote.apiclient.base.RetroNetwork
import com.task.products.data.remote.microservices.products.ProductRetroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Singleton
    @Provides
    fun provideProductService(retroNetwork: RetroNetwork): ProductRetroService {
        return retroNetwork.createService(ProductRetroService::class.java)
    }

}