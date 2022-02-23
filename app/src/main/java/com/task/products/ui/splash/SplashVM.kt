package com.task.products.ui.splash

import com.task.products.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    override val viewState: SplashState
) : BaseViewModel<ISplash.State>(), ISplash.ViewModel