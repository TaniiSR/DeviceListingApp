package com.valet.devicelisting.utils.base


import androidx.lifecycle.MutableLiveData
import com.valet.devicelisting.utils.base.interfaces.IBase
import com.valet.devicelisting.utils.base.sealed.UIEvent

abstract class BaseState : IBase.State {
    override var uiEvent: MutableLiveData<UIEvent> = MutableLiveData()
}