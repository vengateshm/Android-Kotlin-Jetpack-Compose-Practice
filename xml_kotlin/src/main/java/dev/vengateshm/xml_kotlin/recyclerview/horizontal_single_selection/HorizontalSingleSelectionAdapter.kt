package dev.vengateshm.xml_kotlin.recyclerview.horizontal_single_selection

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.xml_kotlin.databinding.HorizontalSingleSelectionItemBinding

class HorizontalSingleSelectionAdapter(
  private val onItemClicked: (Item) -> Unit,
) : ListAdapter<Item, HorizontalSingleSelectionAdapter.ItemViewHolder>(ItemDiffCallback()) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int,
  ): ItemViewHolder {
    val binding = HorizontalSingleSelectionItemBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false,
    )
    return ItemViewHolder(binding, onItemClicked)
  }

  override fun onBindViewHolder(
    holder: ItemViewHolder,
    position: Int,
  ) {
    holder.bind(getItem(position))
  }

  class ItemViewHolder(
    private val binding: HorizontalSingleSelectionItemBinding,
    private val onItemClicked: (Item) -> Unit,
  ) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
      binding.root.setOnClickListener {
        onItemClicked(item)
      }
      binding.textView.text = item.name
      animateItem(binding, item.isSelected)
      rotateText(binding.textView, item.isSelected)
    }

    private fun animateItem(
      binding: HorizontalSingleSelectionItemBinding,
      isSelected: Boolean,
    ) {
      val targetHeight = if (isSelected) 200 else 175
      val targetWidth = if (isSelected) 200 else 50

      val view = binding.itemRoot
//            view.pivotY = view.height.toFloat()

      val shouldAnimate = true
      if (shouldAnimate) {
        val heightAnimator =
          ValueAnimator.ofInt(view.height, dpToPx(view.context, targetHeight))
        val widthAnimator =
          ValueAnimator.ofInt(view.width, dpToPx(view.context, targetWidth))

        heightAnimator
          .apply {
            duration = 300
            interpolator = LinearInterpolator()
          }
          .addUpdateListener {
            val value = it.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()
          }
        widthAnimator
          .apply {
            duration = 300
            interpolator = LinearInterpolator()
          }
          .addUpdateListener {
            val value = it.animatedValue as Int
            view.layoutParams.width = value
            view.requestLayout()
          }
        val playSequentially = false
        if (playSequentially) {
          heightAnimator.start()
          widthAnimator.start()
        } else {
          AnimatorSet().apply {
            playTogether(widthAnimator, heightAnimator)
            interpolator = LinearInterpolator()
            duration = 200
            start()
          }
        }
      } else {
        view.layoutParams.width = dpToPx(view.context, targetWidth)
        view.layoutParams.height = dpToPx(view.context, targetHeight)
        view.requestLayout()
      }
    }

    private fun rotateText(textView: TextView, isSelected: Boolean) {
      val rotation = if (isSelected) 0f else 270f
//            textView.animate().rotation(rotation).setDuration(300).start()
      textView.rotation = rotation
    }

    private fun dpToPx(context: Context, dp: Int): Int {
      return (dp * context.resources.displayMetrics.density).toInt()
    }
  }
}