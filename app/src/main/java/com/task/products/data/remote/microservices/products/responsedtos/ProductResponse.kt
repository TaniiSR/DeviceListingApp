package com.task.products.data.remote.microservices.products.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.task.products.data.models.responsedtos.Header
import com.task.products.data.models.responsedtos.Product
import com.task.products.data.remote.apiclient.base.BaseApiResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(
    @SerializedName("filters")
    val filters: List<String>?,
    @SerializedName("header")
    val header: Header?,
    @SerializedName("products")
    val products: List<Product>?
) : BaseApiResponse(), Parcelable