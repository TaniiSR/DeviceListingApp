package com.task.products.ui.homemain.producthome.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.imageview.ShapeableImageView
import com.task.products.R
import com.task.products.data.models.responsedtos.Header
import com.task.products.data.models.responsedtos.Product
import com.task.products.databinding.LayoutNoDeviceResultItemBinding
import com.task.products.databinding.LayoutProductListAvailableItemBinding
import com.task.products.utils.base.BaseBindingSearchRecyclerAdapter
import com.task.products.utils.base.interfaces.OnItemClickListener
import com.task.products.utils.extensions.loadImage
import java.util.*

class ProductListAdapter(
    private val list: MutableList<Product>,
) : BaseBindingSearchRecyclerAdapter<Product, ProductListAdapter.ViewHolder>(list) {
    private var mHeader: Header? = null
    override fun onCreateViewHolder(binding: ViewBinding): ViewHolder {
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (list.isNullOrEmpty()) holder.onBind(
            Product(),
            position,
            onItemClickListener
        ) else
            holder.onBind(list[position], position, onItemClickListener)
    }

    fun setHeader(header: Header?) {
        mHeader = header
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.isNullOrEmpty())
            R.layout.layout_no_device_result_item
        else R.layout.layout_product_list_available_item
    }


    inner class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: Product,
            position: Int,
            onItemClickListener: OnItemClickListener?
        ) {
            when (binding) {
                is LayoutNoDeviceResultItemBinding -> {
                    handleNoContactResultBinding(binding)
                }
                is LayoutProductListAvailableItemBinding -> {
                    handleSearchBinding(binding, data, position, onItemClickListener)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleSearchBinding(
        binding: LayoutProductListAvailableItemBinding,
        data: Product,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        binding.tvName.text = data.name
        binding.tvHeaderTitle.text = mHeader?.headerTitle
        binding.tvHeaderSubTitle.text = mHeader?.headerDescription
        binding.tvDescription.text = data.description
        binding.tvDescription.text = data.description
        binding.rbRating.rating = data.rating?.toFloat() ?: 0f
        binding.tvPrice.text = "Price ${data.price?.value} ${data.price?.currency}"
        if (position == 0) {
            binding.clHeader.visibility = View.VISIBLE
        } else {
            binding.clHeader.visibility = View.GONE
        }

        if (position == itemCount - 1) {
            binding.clFooter.visibility = View.VISIBLE
        } else {
            binding.clFooter.visibility = View.GONE
        }
        if (data.favorite == true) {
            binding.clMainItem.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.ldAvailable
                )
            )
        }

        if (data.available == true) {
            binding.ivImage.visibility = View.VISIBLE
            binding.ivImageRight.visibility = View.GONE
            loadImage(binding.ivImage, data.imageURL, binding.root.context)
        } else {
            binding.ivImage.visibility = View.GONE
            binding.ivImageRight.visibility = View.VISIBLE
            loadImage(binding.ivImageRight, data.imageURL, binding.root.context)
        }

        binding.clMainItem.setOnClickListener {
            onItemClickListener?.onItemClick(it, data, position)
        }
        binding.clFooter.setOnClickListener {
            onItemClickListener?.onItemClick(it, "footer", position)
        }

    }

    private fun loadImage(ivImage: ShapeableImageView, imageURL: String?, context: Context) {
        ivImage.loadImage(
            resource = imageURL,
            errorRecourse = ContextCompat.getDrawable(
                context,
                R.drawable.ic_launcher_foreground
            ),
            placeRecourse = ContextCompat.getDrawable(
                context,
                R.drawable.ic_launcher_foreground
            )
        )
    }

    private fun handleNoContactResultBinding(
        binding: LayoutNoDeviceResultItemBinding
    ) = Unit

    override fun getItemCount(): Int = if (list.isNullOrEmpty()) 1 else list.size

    override fun filterItem(constraint: CharSequence?, item: Product): Boolean {
        val filterString = constraint.toString().lowercase(Locale.ROOT)
        val deviceName = item.name?.lowercase(Locale.ROOT) ?: ""
        return deviceName.startsWith(filterString) || deviceName.contains(filterString)
    }

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return if (viewType == R.layout.layout_no_device_result_item)
            LayoutNoDeviceResultItemBinding.inflate(layoutInflater, viewGroup, false)
        else LayoutProductListAvailableItemBinding.inflate(layoutInflater, viewGroup, false)
    }
}