package com.task.products.ui.homemain.productdetail

import androidx.lifecycle.LiveData
import com.task.products.data.models.responsedtos.Product
import com.task.products.utils.base.interfaces.IBase

interface IProductDetail {
    interface View : IBase.View<ViewModel> {
        fun getFragmentArguments()
        fun viewModelObservers()
    }

    interface ViewModel : IBase.ViewModel<State>{
        val product : LiveData<Product>
        fun setProduct(device :Product)
        fun setFavourite(isFavourite:Boolean)
    }
    interface State : IBase.State
}