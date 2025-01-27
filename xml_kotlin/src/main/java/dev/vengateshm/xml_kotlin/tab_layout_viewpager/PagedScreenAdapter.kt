package dev.vengateshm.xml_kotlin.tab_layout_viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.xml_kotlin.databinding.PagedItemBinding
import dev.vengateshm.xml_kotlin.tab_layout_viewpager.PagedScreenAdapter.PageViewHolder

class PagedScreenAdapter(
    private var items: List<String>,
) : RecyclerView.Adapter<PageViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PageViewHolder {
        val binding = PagedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PageViewHolder,
        position: Int,
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class PageViewHolder(private val binding: PagedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(string: String) {
            with(binding) {
                pageTextView.text = string
            }
        }
    }

    fun updateData(newItems: List<String>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun updateData1(newItems: List<String>) {
        val diffCallback = PagedScreenDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }
}