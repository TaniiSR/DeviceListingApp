package com.task.products.ui.homemain.producthome

import android.text.TextWatcher
import androidx.lifecycle.LiveData
import com.task.products.data.remote.microservices.products.responsedtos.ProductResponse
import com.task.products.ui.homemain.producthome.adapter.ProductListAdapter
import com.task.products.utils.base.interfaces.IBase

interface IHome {
    interface View : IBase.View<ViewModel> {
        fun viewModelObservers()
    }

    interface ViewModel : IBase.ViewModel<State> {
        val products: LiveData<ProductResponse?>
        val productListAdapter: ProductListAdapter
        val watcher: TextWatcher
        fun getProductList()
    }

    interface State : IBase.State
}