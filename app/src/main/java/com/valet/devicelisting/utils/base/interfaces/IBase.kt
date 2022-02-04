package com.valet.devicelisting.utils.base.interfaces

import androidx.lifecycle.MutableLiveData
import com.valet.devicelisting.utils.base.SingleClickEvent
import com.valet.devicelisting.utils.base.sealed.UIEvent

interface IBase {
    interface View<V : ViewModel<*>> {
        val viewModel: V
    }

    interface ViewModel<S : State> {
        val viewState: S
        val clickEvent: SingleClickEvent
        fun showLoading(onBackGround: Boolean = false)
        fun hideLoading(onBackGround: Boolean = false)
        fun showToast(message: String, onBackGround: Boolean)
        fun showToast(message: String)
    }

    interface State {
        var uiEvent: MutableLiveData<UIEvent>
    }
}