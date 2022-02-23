package com.task.products.ui.homemain.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.task.products.R
import com.task.products.databinding.FragmentWebviewBinding
import com.task.products.utils.WEBVIEW
import com.task.products.utils.base.BaseNavFragment
import com.task.products.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment :
    BaseNavFragment<FragmentWebviewBinding, IWebView.State, IWebView.ViewModel>(
        R.layout.fragment_webview
    ), IWebView.View, ToolBarView.OnToolBarViewClickListener {
    override fun getViewBinding(): FragmentWebviewBinding =
        FragmentWebviewBinding.inflate(layoutInflater)

    override val viewModel: IWebView.ViewModel by viewModels<WebViewVM>()
    override fun onClick(id: Int) = Unit
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        mViewBinding.webView.loadUrl(WEBVIEW)
        mViewBinding.webView.webViewClient = MyWebViewClient()
    }

    override fun onStartIconClicked() {
        navigateBack()
    }
}

private class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}