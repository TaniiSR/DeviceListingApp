package com.valet.devicelisting.ui.home

import com.valet.devicelisting.utils.base.interfaces.IBase

interface IHome {
    interface View : IBase.View<ViewModel>
    interface ViewModel : IBase.ViewModel<State>
    interface State : IBase.State
}