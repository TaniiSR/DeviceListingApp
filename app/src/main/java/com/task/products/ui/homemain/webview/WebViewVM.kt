package com.task.products.ui.homemain.webview

import com.task.products.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewVM @Inject constructor(
    override val viewState: WebViewState
) : BaseViewModel<IWebView.State>(), IWebView.ViewModel
