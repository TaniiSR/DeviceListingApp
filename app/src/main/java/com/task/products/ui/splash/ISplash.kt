package com.task.products.ui.splash

import com.task.products.utils.base.interfaces.IBase

interface ISplash {
    interface View : IBase.View<ViewModel>
    interface ViewModel : IBase.ViewModel<State>
    interface State : IBase.State
}