package com.valet.devicelisting.ui.splash

import com.valet.devicelisting.utils.base.interfaces.IBase

interface ISplash {
    interface View : IBase.View<ViewModel>
    interface ViewModel : IBase.ViewModel<State>
    interface State : IBase.State
}