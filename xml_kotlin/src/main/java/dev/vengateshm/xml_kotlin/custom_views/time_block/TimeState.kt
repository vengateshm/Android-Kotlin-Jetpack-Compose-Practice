package dev.vengateshm.xml_kotlin.custom_views.time_block

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import dev.vengateshm.xml_kotlin.R

enum class TimeState(
    @ColorRes val bgColor: Int,
    @DrawableRes val bgRes1: Int,
    @DrawableRes val bgRes2: Int,
    @DrawableRes val bgRes3: Int,
) {
    A(
        R.color.time_state_bg_a,
        R.drawable.time_block_background_state_a_1,
        R.drawable.time_block_background_state_a_2,
        R.drawable.time_block_background_state_a_3,
    ),
    B(
        R.color.time_state_bg_b,
        R.drawable.time_block_background_state_b_1,
        R.drawable.time_block_background_state_b_2,
        R.drawable.time_block_background_state_b_3,
    );

    companion object {
        fun getState(value: Int) = when (value) {
            1 -> A
            else -> B
        }
    }
}