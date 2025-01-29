package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import androidx.annotation.DimenRes
import dev.vengateshm.xml_kotlin.R
import dev.vengateshm.xml_kotlin.utils.EMPTY_STRING

sealed class ItineraryListItem(
    val viewType: ItineraryListItemType,
    @DimenRes val topOffset: Int = R.dimen.common_ui_spacing_md,
) {
    data class HeaderItem(
        val title: String,
        val subtitle: String,
        @DimenRes val offset: Int = R.dimen.common_ui_spacing_md,
    ) : ItineraryListItem(ItineraryListItemType.HEADER, offset)

    data class BodyItem(
        val activityName: String,
        val time: String,
        val location: String,
        @DimenRes val offset: Int = R.dimen.common_ui_spacing_md,
    ) : ItineraryListItem(ItineraryListItemType.BODY, offset)

    data class FooterItem(
        val summary: String,
        @DimenRes val offset: Int = R.dimen.common_ui_spacing_md,
        val action: String = EMPTY_STRING,
    ) : ItineraryListItem(ItineraryListItemType.FOOTER, offset)
}

enum class ItineraryListItemType {
    HEADER, BODY, FOOTER
}