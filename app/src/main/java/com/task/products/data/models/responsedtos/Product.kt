package com.task.products.data.models.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("available")
    val available: Boolean? = null,
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("colorCode")
    val colorCode: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imageURL")
    val imageURL: String? = null,
    @SerializedName("longDescription")
    val longDescription: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Price? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("releaseDate")
    val releaseDate: Int? = null,
    @SerializedName("type")
    val type: String? = null,
    @Transient
    var favorite: Boolean? = null,
    @Transient
    var position: Int? = null,
) : Parcelable