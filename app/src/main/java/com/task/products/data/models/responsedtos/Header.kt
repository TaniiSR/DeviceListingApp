package com.task.products.data.models.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Header(
    @SerializedName("headerDescription")
    val headerDescription: String?,
    @SerializedName("headerTitle")
    val headerTitle: String?
) : Parcelable