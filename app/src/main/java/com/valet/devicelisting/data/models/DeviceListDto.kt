package com.valet.devicelisting.data.models

import com.google.gson.annotations.SerializedName

data class DeviceListDto(
    @SerializedName("devices")
    val devices: List<DeviceDto>
)
