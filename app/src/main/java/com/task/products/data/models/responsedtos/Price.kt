package com.task.products.data.models.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("value")
    val value: Double?
) : Parcelable