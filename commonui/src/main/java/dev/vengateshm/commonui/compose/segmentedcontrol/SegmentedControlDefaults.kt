package dev.vengateshm.commonui.compose.segmentedcontrol

import androidx.compose.material3.SegmentedButtonColors
import dev.vengateshm.commonui.compose.theme.Black
import dev.vengateshm.commonui.compose.theme.DarkTonalGrey
import dev.vengateshm.commonui.compose.theme.MediumBlue50
import dev.vengateshm.commonui.compose.theme.Transparent
import dev.vengateshm.commonui.compose.theme.VengeanceGrey

object SegmentedControlDefaults {
    private var defaultSingleChoiceSegmentedControlColorCached: SegmentedButtonColors? = null

    fun segmentedButtonColors() : SegmentedButtonColors {
        return defaultSingleChoiceSegmentedControlColorCached ?: SegmentedButtonColors(
            activeContainerColor = MediumBlue50,
            activeContentColor = Black,
            activeBorderColor = MediumBlue50,
            inactiveContainerColor = Transparent,
            inactiveContentColor = VengeanceGrey,
            inactiveBorderColor = DarkTonalGrey,
            disabledActiveContainerColor = MediumBlue50,
            disabledActiveContentColor = Black,
            disabledActiveBorderColor = MediumBlue50,
            disabledInactiveContainerColor = Transparent,
            disabledInactiveContentColor = VengeanceGrey,
            disabledInactiveBorderColor = DarkTonalGrey
        )
    }
}