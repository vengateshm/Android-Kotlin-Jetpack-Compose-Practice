package dev.vengateshm.xml_kotlin.recyclerview.horizontal_single_selection

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.vengateshm.xml_kotlin.R

class HorizontalSingleSelectionScrollViewFragment : Fragment() {

  private val viewModel: HorizontalSingleSelectionViewModel by viewModels()
  private lateinit var itemContainer: LinearLayout
  private val itemViews = mutableListOf<View>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    return inflater.inflate(
      R.layout.fragment_horizontal_single_selection_list_scroll_view,
      container,
      false,
    )
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    itemContainer = view.findViewById(R.id.itemContainer)
    viewModel.items.observe(viewLifecycleOwner) { items ->
      itemContainer.removeAllViews()
      itemViews.clear()
      items.forEachIndexed { index, item ->
        val view = LayoutInflater.from(context)
          .inflate(R.layout.horizontal_single_selection_item, itemContainer, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        val itemRoot = view.findViewById<FrameLayout>(R.id.itemRoot)
        textView.text = item.name

        updateView(itemRoot, textView, item.isSelected)

        view.setOnClickListener {
          viewModel.onItemClicked(item)
        }
        itemContainer.addView(view)
        itemViews.add(view)
      }
    }
  }

  private fun updateView(view: View, textView: TextView, isSelected: Boolean) {
    val targetHeight = dpToPx(if (isSelected) 200 else 175)
    val targetWidth = dpToPx(if (isSelected) 200 else 50)

    animateDimension(view, targetWidth, targetHeight)

    val targetRotation = if (isSelected) 0f else 270f
    //textView.animate().rotation(targetRotation).setDuration(300).start()
    textView.rotation = targetRotation
  }

  private fun animateDimension(view: View, targetWidth: Int, targetHeight: Int) {
    val shouldAnimate = true
    if (shouldAnimate) {
      val heightAnimator = ValueAnimator.ofInt(view.height, targetHeight)
      val widthAnimator = ValueAnimator.ofInt(view.width, targetWidth)

      heightAnimator.addUpdateListener {
        view.layoutParams.height = it.animatedValue as Int
        view.requestLayout()
      }

      widthAnimator.addUpdateListener {
        view.layoutParams.width = it.animatedValue as Int
        view.requestLayout()
      }

      heightAnimator.apply {
        duration = 300
        interpolator = LinearInterpolator()
      }
      widthAnimator.apply {
        duration = 300
        interpolator = LinearInterpolator()
      }
      val playSequentially = false
      if (playSequentially) {
        heightAnimator.start()
        widthAnimator.start()
      } else {
        AnimatorSet().apply {
          playTogether(widthAnimator, heightAnimator)
          interpolator = BounceInterpolator()
          duration = 200
          start()
        }
      }
    } else {
      view.layoutParams.width = targetWidth
      view.layoutParams.height = targetHeight
      view.requestLayout()
    }
  }

  private fun dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
  }
}