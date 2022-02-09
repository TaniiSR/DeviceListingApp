package com.valet.devicelisting.ui.homemain

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.valet.devicelisting.R
import com.valet.devicelisting.databinding.ActivityHomeMainBinding
import com.valet.devicelisting.utils.base.BaseNavActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMainActivity :
    BaseNavActivity<ActivityHomeMainBinding, IHomeMain.State, IHomeMain.ViewModel>(),
    IHomeMain.View {
    override fun getViewBinding(): ActivityHomeMainBinding =
        ActivityHomeMainBinding.inflate(layoutInflater)

    override val viewModel: IHomeMain.ViewModel by viewModels<HomeMainVM>()
    override val navigationGraphId: Int
        get() = R.navigation.device_navigation

    override fun onDestinationChanged(
        controller: NavController?,
        destination: NavDestination?,
        arguments: Bundle?
    ) = Unit

}