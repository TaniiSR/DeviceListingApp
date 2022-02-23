package com.task.products.data.remote.microservices.products

import com.task.products.data.remote.apiclient.base.ApiResponse
import com.task.products.data.remote.microservices.products.responsedtos.ProductResponse

interface ProductApi {
    suspend fun getProducts(): ApiResponse<ProductResponse>
}