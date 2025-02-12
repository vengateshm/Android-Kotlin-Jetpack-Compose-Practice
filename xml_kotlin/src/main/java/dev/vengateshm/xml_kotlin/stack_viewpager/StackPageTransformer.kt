package dev.vengateshm.xml_kotlin.stack_viewpager

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class StackPageTransformer : ViewPager2.PageTransformer {
    //    override fun transformPage(page: View, position: Float) {
//        val scaleFactor = 1 - 0.2f * abs(position)  // Scale down effect
//        page.scaleY = scaleFactor
//
//        page.translationX = -position * page.width * 0.7f  // Stacking effect
//        page.alpha = 1 - abs(position) * 0.5f  // Fade effect
//    }
//    override fun transformPage(page: View, position: Float) {
//        if (position <= 0) {
//            // Current page: Bring to the front
//            page.translationZ = 1f
//            page.scaleY = 1.0f
//        } else {
//            // Next page: Move below current page
//            page.translationZ = -position
//            page.scaleY = 0.9f // Slight shrink effect
//        }
//    }
    override fun transformPage(page: View, position: Float) {
        when {
            position <= 0 -> {
                // Current page (normal state)
                page.translationX = 0f
                page.translationZ = 1f // Keep it above the next page
            }

            position > 0 -> {
                // Next page (move it behind and shift in X direction)
                page.translationX = -position * page.width // Move left as it swipes
                page.translationZ = -1f // Push it behind the current page
            }
        }
    }
}
