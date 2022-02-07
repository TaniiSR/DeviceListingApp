package com.valet.devicelisting.ui.home

import androidx.activity.viewModels
import com.valet.devicelisting.databinding.ActivityHomeBinding
import com.valet.devicelisting.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity :
    BaseActivity<ActivityHomeBinding, IHome.State, IHome.ViewModel>(),
    IHome.View {
    override fun getViewBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)
    override val viewModel: IHome.ViewModel by viewModels<HomeVM>()

}