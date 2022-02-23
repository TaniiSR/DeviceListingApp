package com.task.products.ui.homemain

import com.task.products.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeMainVM @Inject constructor(
    override val viewState: HomeMainState
) : BaseViewModel<IHomeMain.State>(), IHomeMain.ViewModel