package dev.vengateshm.xml_kotlin.stack_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import dev.vengateshm.xml_kotlin.R

class StackViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_stack_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        // Sample data for pages
        val pageData = listOf("Page 1", "Page 2", "Page 3", "Page 4", "Page 5")

        val adapter = StackViewPagerAdapter(pageData)
        viewPager.adapter = adapter

        // Stack effect
        viewPager.offscreenPageLimit = 3
        viewPager.setPageTransformer(StackPageTransformer())
    }
}