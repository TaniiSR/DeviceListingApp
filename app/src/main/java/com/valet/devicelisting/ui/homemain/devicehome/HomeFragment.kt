package com.valet.devicelisting.ui.homemain.devicehome

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.valet.devicelisting.R
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.databinding.FragmentHomeBinding
import com.valet.devicelisting.utils.base.BaseNavFragment
import com.valet.devicelisting.utils.base.interfaces.OnItemClickListener
import com.valet.devicelisting.utils.extensions.observe
import com.valet.devicelisting.utils.uikit.searchview.SearchView
import com.valet.devicelisting.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseNavFragment<FragmentHomeBinding, IHome.State, IHome.ViewModel>(
        R.layout.fragment_home
    ), IHome.View, ToolBarView.OnToolBarViewClickListener, SearchView.OnSearchViewClickListener {
    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override val viewModel: IHome.ViewModel by viewModels<HomeVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        mViewBinding.searchView.setOnToolBarViewClickListener(this)
        mViewBinding.searchView.textWatcher = viewModel.watcher
        setUpRecyclerView()
        viewModel.getDeviceList()
    }

    private fun setUpRecyclerView() {
        viewModel.deviceListAdapter.allowFullItemClickListener = true
        viewModel.deviceListAdapter.onItemClickListener = rvItemClickListener
        mViewBinding.rvDevices.adapter = viewModel.deviceListAdapter
    }

    override fun onEndIconClicked() {
        mViewBinding.searchView.openSearch()
        mViewBinding.tbView.visibility = View.INVISIBLE
    }

    override fun onCancelClicked() {
        mViewBinding.searchView.closeSearch()
        mViewBinding.tbView.visibility = View.VISIBLE
        viewModel.deviceListAdapter.filter.filter("")
    }

    override fun onClick(id: Int) = Unit

    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            when (data) {
                is DeviceDto -> {
                    navigate(
                        R.id.action_homeFragment_to_deviceDetailFragment,
                        bundleOf(DeviceDto::class.java.name to data)
                    )
                }
            }
        }
    }

    private fun handleDevices(list: List<DeviceDto>?) {
        list?.let {
            viewModel.deviceListAdapter.setList(it)
        }
    }

    override fun viewModelObservers() {
        observe(viewModel.deviceList, ::handleDevices)
    }
}