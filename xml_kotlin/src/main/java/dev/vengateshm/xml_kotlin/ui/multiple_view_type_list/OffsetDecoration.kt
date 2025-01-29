package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetDecoration(
    private val provider: OffsetProvider,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val viewPosition = parent.getChildAdapterPosition(view)
        if (viewPosition == RecyclerView.NO_POSITION) {
            return
        }
        outRect.top =
            view.resources.getDimensionPixelSize(provider.getDecorationTopOffset(viewPosition))
        outRect.bottom =
            view.resources.getDimensionPixelSize(provider.getDecorationBottomOffset(viewPosition))
        outRect.left =
            view.resources.getDimensionPixelSize(provider.getDecorationStartOffset(viewPosition))
        outRect.right =
            view.resources.getDimensionPixelSize(provider.getDecorationEndOffset(viewPosition))
    }
}