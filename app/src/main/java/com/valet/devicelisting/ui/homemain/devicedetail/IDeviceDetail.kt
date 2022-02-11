package com.valet.devicelisting.ui.homemain.devicedetail

import androidx.lifecycle.LiveData
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.utils.base.interfaces.IBase

interface IDeviceDetail {
    interface View : IBase.View<ViewModel> {
        fun getFragmentArguments()
        fun viewModelObservers()
    }

    interface ViewModel : IBase.ViewModel<State>{
        val device : LiveData<DeviceDto>
        fun setDevice(device :DeviceDto)
    }
    interface State : IBase.State
}