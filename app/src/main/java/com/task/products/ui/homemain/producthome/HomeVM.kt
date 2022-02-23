package com.task.products.ui.homemain.producthome

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.products.data.remote.microservices.products.responsedtos.ProductResponse
import com.task.products.domain.IDataRepository
import com.task.products.ui.homemain.producthome.adapter.ProductListAdapter
import com.task.products.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    override val viewState: HomeState,
    private val dataRepository: IDataRepository
) : BaseViewModel<IHome.State>(), IHome.ViewModel {

    private val _products = MutableLiveData<ProductResponse?>()
    override val products: LiveData<ProductResponse?> = _products

    override val productListAdapter: ProductListAdapter = ProductListAdapter(mutableListOf())

    override fun getProductList() {
        launch {
            showLoading(onBackGround = true)
            val products = dataRepository.getProducts()
            withContext(Dispatchers.Main) {
                when (products) {
                    null -> {
                        _products.value = null
                        hideLoading()
                        showToast("Sorry Something went wrong")
                    }
                    else -> {
                        hideLoading()
                        // Show error from response
                        _products.value = products
                    }
                }
            }
        }
    }

    override val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        override fun afterTextChanged(s: Editable?) {
            productListAdapter.filter.filter(s)
        }
    }

}