package com.task.products.ui.homemain.webview

import com.task.products.utils.base.interfaces.IBase

interface IWebView {
    interface View : IBase.View<ViewModel>

    interface ViewModel : IBase.ViewModel<State>
    interface State : IBase.State
}