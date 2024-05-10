package dev.vengateshm.xml_kotlin.tab_layout_viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.vengateshm.xml_kotlin.databinding.ImageGridItemBinding

class ImageUiDataListAdapter :
    RecyclerView.Adapter<ImageUiDataListAdapter.ImageUiDataViewHolder>() {

    var imageUiDataList = mutableListOf<ImageUiData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageUiDataViewHolder {
        val binding = ImageGridItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ImageUiDataViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageUiDataList.size
    }

    override fun onBindViewHolder(holder: ImageUiDataViewHolder, position: Int) {
        holder.bind(imageUiDataList[position])
    }

    class ImageUiDataViewHolder(private val binding: ImageGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUiData: ImageUiData) {
            Glide.with(binding.ivImage)
                .load(imageUiData.imageRes)
                .into(binding.ivImage)
            binding.ivDownload.isVisible = !imageUiData.isDownloaded
        }
    }
}