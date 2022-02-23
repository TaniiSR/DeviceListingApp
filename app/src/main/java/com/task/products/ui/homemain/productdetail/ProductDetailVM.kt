package com.task.products.ui.homemain.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.products.data.models.responsedtos.Product
import com.task.products.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailVM @Inject constructor(
    override val viewState: ProductDetailState
) : BaseViewModel<IProductDetail.State>(), IProductDetail.ViewModel {

    private val _product: MutableLiveData<Product> = MutableLiveData()
    override val product: LiveData<Product> = _product

    override fun setProduct(product: Product) {
        _product.value = product
    }

    override fun setFavourite(isFavourite: Boolean) {
        _product.value?.favorite = isFavourite
        setProduct(_product.value ?: Product())
    }


}