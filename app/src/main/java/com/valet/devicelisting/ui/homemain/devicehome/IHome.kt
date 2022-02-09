package com.valet.devicelisting.ui.homemain.devicehome

import android.text.TextWatcher
import androidx.lifecycle.LiveData
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.ui.homemain.devicehome.adapter.DeviceListAdapter
import com.valet.devicelisting.utils.base.interfaces.IBase

interface IHome {
    interface View : IBase.View<ViewModel> {
        fun viewModelObservers()
    }

    interface ViewModel : IBase.ViewModel<State> {
        val deviceList: LiveData<List<DeviceDto>?>
        val deviceListAdapter: DeviceListAdapter
        val watcher: TextWatcher
        fun getDeviceList()
    }

    interface State : IBase.State
}