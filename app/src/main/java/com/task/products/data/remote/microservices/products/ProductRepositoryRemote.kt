package com.task.products.data.remote.microservices.products

import com.task.products.data.remote.apiclient.base.ApiResponse
import com.task.products.data.remote.apiclient.base.BaseRepository
import com.task.products.data.remote.microservices.products.responsedtos.ProductResponse
import javax.inject.Inject

class ProductRepositoryRemote @Inject constructor(
    private val service: ProductRetroService
) : BaseRepository(), ProductApi {


    companion object {
        const val URL_GET_PRODUCTS = "/products-test.json"
    }

    override suspend fun getProducts(): ApiResponse<ProductResponse> {
        return executeSafely(call = {
            service.getProducts()
        })
    }
}