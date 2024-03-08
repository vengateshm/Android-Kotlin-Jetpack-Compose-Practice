package dev.vengateshm.compose_material3.multi_select_list_view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class MultiSelectionState(initialIsMultiSelectModeEnabled: Boolean = false) {
    var isMultiSelectModeEnabled by mutableStateOf(value = initialIsMultiSelectModeEnabled)
}

@Composable
fun rememberMultiSelectionState(): MultiSelectionState {
    return rememberSaveable(saver = MultiSelectionStateSaver) {
        MultiSelectionState()
    }
}

object MultiSelectionStateSaver : Saver<MultiSelectionState, Boolean> {
    override fun restore(value: Boolean): MultiSelectionState {
        return MultiSelectionState(value)
    }

    override fun SaverScope.save(value: MultiSelectionState): Boolean {
        return value.isMultiSelectModeEnabled
    }
}