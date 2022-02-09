package com.valet.devicelisting.domain

import com.valet.devicelisting.data.models.DeviceDto

interface IDataRepository {
   suspend fun getDeviceList(): List<DeviceDto>
}