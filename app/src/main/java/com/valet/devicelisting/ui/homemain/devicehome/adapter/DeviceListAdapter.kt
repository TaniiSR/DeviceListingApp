package com.valet.devicelisting.ui.homemain.devicehome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.valet.devicelisting.R
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.databinding.LayoutDeviceListItemBinding
import com.valet.devicelisting.databinding.LayoutNoDeviceResultItemBinding
import com.valet.devicelisting.utils.base.BaseBindingSearchRecyclerAdapter
import com.valet.devicelisting.utils.base.interfaces.OnItemClickListener
import com.valet.devicelisting.utils.loadImage
import java.util.*

class DeviceListAdapter(
    private val list: MutableList<DeviceDto>
) : BaseBindingSearchRecyclerAdapter<DeviceDto, DeviceListAdapter.ViewHolder>(list) {

    override fun onCreateViewHolder(binding: ViewBinding): ViewHolder {
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (list.isNullOrEmpty()) holder.onBind(
            DeviceDto(),
            position,
            onItemClickListener
        ) else
            holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.isNullOrEmpty())
            R.layout.layout_no_device_result_item
        else R.layout.layout_device_list_item
    }


    inner class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: DeviceDto,
            position: Int,
            onItemClickListener: OnItemClickListener?
        ) {
            when (binding) {
                is LayoutNoDeviceResultItemBinding -> {
                    handleNoContactResultBinding(binding)
                }
                is LayoutDeviceListItemBinding -> {
                    handleSearchBinding(binding, data, position, onItemClickListener)
                }
            }
        }
    }

    private fun handleSearchBinding(
        binding: LayoutDeviceListItemBinding,
        data: DeviceDto,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        binding.tvName.text = data.title
        binding.tvDescription.text = data.description
        binding.ivImage.loadImage(
            resource = data.imageUrl,
            errorRecourse = ContextCompat.getDrawable(
                binding.root.context,
                R.drawable.ic_launcher_foreground
            ),
            placeRecourse = ContextCompat.getDrawable(
                binding.root.context,
                R.drawable.ic_launcher_foreground
            )
        )
    }

    private fun handleNoContactResultBinding(
        binding: LayoutNoDeviceResultItemBinding
    ) = Unit

    override fun getItemCount(): Int = if (list.isNullOrEmpty()) 1 else list.size

    override fun filterItem(constraint: CharSequence?, item: DeviceDto): Boolean {
        val filterString = constraint.toString().lowercase(Locale.ROOT)
        val deviceName = item.title?.lowercase(Locale.ROOT) ?: ""
        return deviceName.startsWith(filterString) || deviceName.contains(filterString)
    }

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return if (viewType == R.layout.layout_no_device_result_item)
            LayoutNoDeviceResultItemBinding.inflate(layoutInflater, viewGroup, false)
        else LayoutDeviceListItemBinding.inflate(layoutInflater, viewGroup, false)
    }
}