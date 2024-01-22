package dev.vengateshm.android_kotlin_compose_practice.games.color_finder

import androidx.compose.ui.graphics.Color

object ColorFinderUtils {
    private val redColor = Color(0xFFFF0000)
    private val greenColor = Color(0xFF00FF00)
    private val blueColor = Color(0xFF0000FF)
    private val yellowColor = Color(0xFFFFFF00)
    private val blackColor = Color(0xFF000000)
    private val orangeColor = Color(0xFFFF4F00)
    private val purpleColor = Color(0xFF800080)
    private val grayColor = Color(0xFF808080)
    private val brownColor = Color(0xFFA52A2A)

    private val colorPair: List<Pair<String, Color>> by lazy {
        listOf(
            "Red" to redColor,
            "Green" to greenColor,
            "Blue" to blueColor,
            "Yellow" to yellowColor,
            "Black" to blackColor,
            "Orange" to orangeColor,
            "Purple" to purpleColor,
            "Gray" to grayColor,
            "Brown" to brownColor,
        )
    }

    fun getColorNameAndList(): ColorNameAndList {
        val shuffled = colorPair.shuffled().take(6)
        val colorName = shuffled.random().first
        return ColorNameAndList(
            colorName = colorName,
            colorNameTextColor = colorPair.shuffled().random().second,
            colorList = shuffled,
        )
    }
}

data class ColorNameAndList(
    val colorName: String,
    val colorNameTextColor: Color,
    val colorList: List<Pair<String, Color>>,
)
