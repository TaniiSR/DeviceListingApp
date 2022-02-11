package com.valet.devicelisting.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeviceDto(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("isFavorite")
    val isFavorite: Boolean = false
) : Parcelable