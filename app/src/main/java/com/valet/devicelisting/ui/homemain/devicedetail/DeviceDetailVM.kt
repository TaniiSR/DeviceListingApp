package com.valet.devicelisting.ui.homemain.devicedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.domain.IDataRepository
import com.valet.devicelisting.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceDetailVM @Inject constructor(
    override val viewState: DeviceDetailState,
    private val dataRepository: IDataRepository
) : BaseViewModel<IDeviceDetail.State>(), IDeviceDetail.ViewModel {

    private val _device: MutableLiveData<DeviceDto> = MutableLiveData()
    override val device: LiveData<DeviceDto> = _device

    override fun setDevice(device: DeviceDto) {
        _device.value = device
    }

}