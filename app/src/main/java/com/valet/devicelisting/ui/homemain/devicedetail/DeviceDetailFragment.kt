package com.valet.devicelisting.ui.homemain.devicedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.valet.devicelisting.R
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.databinding.FragmentDeviceDetailBinding
import com.valet.devicelisting.utils.base.BaseNavFragment
import com.valet.devicelisting.utils.extensions.observe
import com.valet.devicelisting.utils.loadImage
import com.valet.devicelisting.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceDetailFragment :
    BaseNavFragment<FragmentDeviceDetailBinding, IDeviceDetail.State, IDeviceDetail.ViewModel>(
        R.layout.fragment_device_detail
    ), IDeviceDetail.View, ToolBarView.OnToolBarViewClickListener {
    override fun getViewBinding(): FragmentDeviceDetailBinding =
        FragmentDeviceDetailBinding.inflate(layoutInflater)

    override fun getFragmentArguments() {
        arguments?.let {
            val deviceDto = it.getParcelable<DeviceDto>(DeviceDto::class.java.name)
            deviceDto?.let { device ->
                viewModel.setDevice(device)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
        getFragmentArguments()
    }

    override val viewModel: IDeviceDetail.ViewModel by viewModels<DeviceDetailVM>()

    override fun onClick(id: Int) = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
    }

    override fun onStartIconClicked() {
        navigateBack()
    }

    @SuppressLint("SetTextI18n")
    private fun handleDevice(deviceDto: DeviceDto) {
        mViewBinding.tvDescription.text = deviceDto.description
        mViewBinding.tvName.text = deviceDto.title
        mViewBinding.tvPrice.text = "${deviceDto.price} ${deviceDto.currency}"
        mViewBinding.ivImage.loadImage(
            resource = deviceDto.imageUrl,
            errorRecourse = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_launcher_foreground
            ),
            placeRecourse = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_launcher_foreground
            )
        )
    }

    override fun viewModelObservers() {
        observe(viewModel.device, ::handleDevice)
    }
}