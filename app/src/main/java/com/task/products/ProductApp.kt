package com.task.products

import android.app.Application
import com.task.products.data.remote.apiclient.base.RetroNetwork
import com.task.products.utils.BASE_URL
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ProductApp : Application() {
    @Inject
    lateinit var retroNetwork: RetroNetwork
    override fun onCreate() {
        super.onCreate()
        retroNetwork.build(BASE_URL)
    }
}