package com.task.products.data.remote.apiclient.base.interfaces


import com.task.products.data.remote.apiclient.base.ApiResponse
import com.task.products.data.remote.apiclient.base.BaseApiResponse
import retrofit2.Response

internal interface IRepository {
    suspend fun <T : BaseApiResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
}