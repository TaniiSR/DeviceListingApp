package com.valet.devicelisting.ui.home

import com.valet.devicelisting.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    override val viewState: HomeState
) : BaseViewModel<IHome.State>(), IHome.ViewModel