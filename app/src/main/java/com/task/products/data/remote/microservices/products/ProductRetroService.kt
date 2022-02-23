package com.task.products.data.remote.microservices.products

import com.task.products.data.remote.microservices.products.responsedtos.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductRetroService {
    //Get the products
    @GET(ProductRepositoryRemote.URL_GET_PRODUCTS)
    suspend fun getProducts(): Response<ProductResponse>
}