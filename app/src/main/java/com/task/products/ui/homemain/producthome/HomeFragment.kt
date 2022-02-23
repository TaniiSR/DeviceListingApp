package com.task.products.ui.homemain.producthome

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.products.R
import com.task.products.data.models.responsedtos.Product
import com.task.products.data.remote.microservices.products.responsedtos.ProductResponse
import com.task.products.databinding.FragmentHomeBinding
import com.task.products.utils.base.BaseNavFragment
import com.task.products.utils.base.interfaces.OnItemClickListener
import com.task.products.utils.extensions.observe
import com.task.products.utils.uikit.searchview.SearchView
import com.task.products.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseNavFragment<FragmentHomeBinding, IHome.State, IHome.ViewModel>(
        R.layout.fragment_home
    ), IHome.View, ToolBarView.OnToolBarViewClickListener, SearchView.OnSearchViewClickListener,
    SwipeRefreshLayout.OnRefreshListener {
    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override val viewModel: IHome.ViewModel by viewModels<HomeVM>()
    private val requestCode = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        mViewBinding.searchView.setOnToolBarViewClickListener(this)
        mViewBinding.searchView.textWatcher = viewModel.watcher
        mViewBinding.swRefresh.setOnRefreshListener(this)
        setUpRecyclerView()
        if (viewModel.products.value == null) viewModel.getProductList()
        setOnclick()
    }

    private fun setOnclick() {
        mViewBinding.tvAll.setOnClickListener {
            handleProducts(
                ProductResponse(
                    header = viewModel.products.value?.header,
                    filters = viewModel.products.value?.filters,
                    products = viewModel.products.value?.products
                )
            )
        }

        mViewBinding.tvAvailable.setOnClickListener {
            handleProducts(
                ProductResponse(
                    header = viewModel.products.value?.header,
                    filters = viewModel.products.value?.filters,
                    products = viewModel.products.value?.products?.filter { it.available == true }
                )
            )
        }

        mViewBinding.tvFavourite.setOnClickListener {
            handleProducts(
                ProductResponse(
                    header = viewModel.products.value?.header,
                    filters = viewModel.products.value?.filters,
                    products = viewModel.products.value?.products?.filter { it.favorite == true }
                )
            )
        }
    }

    private fun setUpRecyclerView() {
        viewModel.productListAdapter.onItemClickListener = rvItemClickListener
        mViewBinding.rvDevices.adapter = viewModel.productListAdapter
    }

    override fun onEndIconClicked() {
        mViewBinding.searchView.openSearch()
        mViewBinding.tbView.visibility = View.INVISIBLE
    }

    override fun onCancelClicked() {
        mViewBinding.searchView.closeSearch()
        mViewBinding.tbView.visibility = View.VISIBLE
        viewModel.productListAdapter.filter.filter("")
    }

    override fun onClick(id: Int) = Unit

    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            when (data) {
                is Product -> {
                    data.position = pos
                    navigateForResult(
                        resId = R.id.action_homeFragment_to_deviceDetailFragment,
                        args = bundleOf(Product::class.java.name to data),
                        requestCode = requestCode
                    )
                }
                is String -> {
                    navigate(R.id.action_homeFragment_to_webViewFragment)
                }
            }
        }
    }

    private fun handleProducts(products: ProductResponse?) {
        products?.let { prod ->
            viewModel.productListAdapter.setHeader(prod.header)
            viewModel.productListAdapter.setList(prod.products ?: listOf<Product>())
        }
    }

    override fun viewModelObservers() {
        observe(viewModel.products, ::handleProducts)
    }

    override fun onRefresh() {
        viewModel.getProductList()
        mViewBinding.swRefresh.isRefreshing = false
    }
}