package dev.vengateshm.commonui.base.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import dev.vengateshm.commonui.R
import dev.vengateshm.commonui.base.fragment.CommonUIFragment
import dev.vengateshm.commonui.compose.theme.CommonUiAppTheme

abstract class BaseComposeFragment : CommonUIFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.common_ui_fragment_base_compose, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
        val composeView = view as ComposeView
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                CommonUiAppTheme {
                    SideEffects()
                    Column {
                        if (isShowOtherContent) {
                            OtherContent()
                        }
                        ComposeContent()
                    }
                }
            }
        }
    }

    open fun setUpFragment() {

    }

    @Stable
    @Composable
    open fun SideEffects() {

    }

    @Composable
    abstract fun ComposeContent()

    @Composable
    open fun OtherContent() {

    }

    open var isShowOtherContent: Boolean = true
}