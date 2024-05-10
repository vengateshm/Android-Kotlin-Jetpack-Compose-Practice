package dev.vengateshm.xml_kotlin.tab_layout_viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImagePagerAdapter(
    fragmentActivity: FragmentActivity,
    private var tabImageDataList: List<TabImageData>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tabImageDataList.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageGridFragment()
            .apply {
                tabImageData = tabImageDataList[position]
            }
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    fun updateData(updatedTabImageDataList: List<TabImageData>) {
        tabImageDataList = updatedTabImageDataList
    }
}