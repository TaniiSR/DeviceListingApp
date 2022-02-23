package com.task.products.domain

import com.task.products.data.remote.microservices.products.responsedtos.ProductResponse

interface IDataRepository {
    suspend fun getProducts(): ProductResponse?
}