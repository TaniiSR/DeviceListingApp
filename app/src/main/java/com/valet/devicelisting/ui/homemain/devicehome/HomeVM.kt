package com.valet.devicelisting.ui.homemain.devicehome

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.domain.IDataRepository
import com.valet.devicelisting.ui.homemain.devicehome.adapter.DeviceListAdapter
import com.valet.devicelisting.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    override val viewState: HomeState,
    private val dataRepository: IDataRepository
) : BaseViewModel<IHome.State>(), IHome.ViewModel {

    private val _deviceList = MutableLiveData<List<DeviceDto>?>()
    override val deviceList: LiveData<List<DeviceDto>?> = _deviceList

    override val deviceListAdapter: DeviceListAdapter = DeviceListAdapter(mutableListOf())

    override fun getDeviceList() {
        launch {
            showLoading(onBackGround = true)
            delay(2000)
            val response = dataRepository.getDeviceList()
            withContext(Dispatchers.Main) {
                when (response.isNullOrEmpty()) {
                    true -> {
                        hideLoading()
                        // Show error from response
                        _deviceList.value = null
                        showToast("Sorry Something went wrong")
                    }
                    false -> {
                        _deviceList.value = response
                        hideLoading()
                    }
                }
            }
        }
    }

    override val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        override fun afterTextChanged(s: Editable?) {
            deviceListAdapter.filter.filter(s)
        }
    }

}