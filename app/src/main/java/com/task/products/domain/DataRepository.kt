package com.task.products.domain

import android.content.Context
import com.task.products.data.remote.apiclient.base.ApiResponse
import com.task.products.data.remote.microservices.products.ProductApi
import com.task.products.data.remote.microservices.products.responsedtos.ProductResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val productApi: ProductApi
) :
    IDataRepository {
    var count = 1
    override suspend fun getProducts(): ProductResponse? {
        return when (val response = productApi.getProducts()) {
            is ApiResponse.Success -> {
                response.data
                if (count < 3) {
                    count = count.plus(1)
                    response.data
                } else {
                    count = 0
                    null
                }
            }
            is ApiResponse.Error -> {
                null
            }
        }
    }

}