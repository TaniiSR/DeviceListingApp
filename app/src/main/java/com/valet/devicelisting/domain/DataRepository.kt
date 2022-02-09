package com.valet.devicelisting.domain

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.data.models.DeviceListDto
import com.valet.devicelisting.utils.DEVICE_RESPONSE_JSON
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataRepository @Inject constructor(@ApplicationContext val context: Context) :
    IDataRepository {
    override suspend fun getDeviceList(): List<DeviceDto> {
        return getDeviceListResources().devices
    }

    private fun getDeviceListResources(): DeviceListDto {
        val gson = GsonBuilder().create()
        val jsonString = context.assets.open(DEVICE_RESPONSE_JSON).bufferedReader().use {
            it.readText()
        }
        val itemType = object : TypeToken<DeviceListDto>() {}.type
        return gson.fromJson(jsonString, itemType)
    }
}