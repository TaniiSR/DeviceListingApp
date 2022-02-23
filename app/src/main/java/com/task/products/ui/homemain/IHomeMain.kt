package com.task.products.ui.homemain

import com.task.products.utils.base.interfaces.IBase

interface IHomeMain {
    interface View : IBase.View<ViewModel>
    interface ViewModel : IBase.ViewModel<State>
    interface State : IBase.State
}