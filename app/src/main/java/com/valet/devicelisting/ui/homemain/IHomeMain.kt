package com.valet.devicelisting.ui.homemain

import com.valet.devicelisting.utils.base.interfaces.IBase

interface IHomeMain {
    interface View : IBase.View<ViewModel>
    interface ViewModel : IBase.ViewModel<State>
    interface State : IBase.State
}