package com.task.products.ui.homemain.productdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.task.products.R
import com.task.products.data.models.responsedtos.Product
import com.task.products.databinding.FragmentProductDetailBinding
import com.task.products.utils.base.BaseNavFragment
import com.task.products.utils.extensions.loadImage
import com.task.products.utils.extensions.observe
import com.task.products.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment :
    BaseNavFragment<FragmentProductDetailBinding, IProductDetail.State, IProductDetail.ViewModel>(
        R.layout.fragment_product_detail
    ), IProductDetail.View, ToolBarView.OnToolBarViewClickListener {
    override fun getViewBinding(): FragmentProductDetailBinding =
        FragmentProductDetailBinding.inflate(layoutInflater)

    override fun getFragmentArguments() {
        arguments?.let {
            val product = it.getParcelable<Product>(Product::class.java.name)
            product?.let { prod ->
                viewModel.setProduct(prod)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
    }

    override val viewModel: IProductDetail.ViewModel by viewModels<ProductDetailVM>()

    override fun onClick(id: Int) = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        getFragmentArguments()
        setBackButtonDispatcher()
    }

    override fun onStartIconClicked() {
        navigateBack()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    @SuppressLint("SetTextI18n")
    private fun handleDevice(product: Product) {
        mViewBinding.tvDescription.text = product.description
        mViewBinding.tvDescriptionLong.text = product.longDescription
        mViewBinding.tvName.text = product.name
        mViewBinding.rbRating.rating = product.rating?.toFloat() ?: 0f
        mViewBinding.tvPrice.text = "Price ${product.price?.value} ${product.price?.currency}"
        mViewBinding.ivImage.loadImage(
            resource = product.imageURL,
            errorRecourse = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_launcher_foreground
            ),
            placeRecourse = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_launcher_foreground
            )
        )
        if (product.favorite == true) {
            mViewBinding.btMarkFavourite.text = "Vergessen"
            mViewBinding.tvName.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.ldBrightBlue
                )
            )
        } else {
            mViewBinding.btMarkFavourite.text = "Vormerken"
            mViewBinding.tvName.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.ldDarkSlateBlue
                )
            )
        }
        mViewBinding.btMarkFavourite.setOnClickListener {
            viewModel.setFavourite(!(viewModel.product.value?.favorite ?: false))
        }
        mViewBinding.clFooter.setOnClickListener {
            navigate(R.id.action_deviceDetailFragment_to_webViewFragment)
        }
    }

    override fun viewModelObservers() {
        observe(viewModel.product, ::handleDevice)
    }
}