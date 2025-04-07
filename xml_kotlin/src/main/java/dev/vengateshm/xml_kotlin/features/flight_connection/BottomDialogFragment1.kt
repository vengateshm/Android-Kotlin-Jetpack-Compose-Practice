package dev.vengateshm.xml_kotlin.features.flight_connection

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import dev.vengateshm.commonui.base.BaseBottomSheetDialogFragment
import dev.vengateshm.xml_kotlin.R

class BottomDialogFragment1 : BaseBottomSheetDialogFragment() {
    override val layoutResourceId = R.layout.fragment_bottom_dialog_1

    private val viewModel by lazy {
        obtainViewModel {
            BottomDialogFragment1ViewModel()
        }
    }

    private val sharedViewModel1: SharedViewModel1 by createSharedViewModel1()

    private fun createSharedViewModel1() = lazy {
        findNavController().getBackStackEntry(R.id.flight_connection_graph)
        navGraphViewModels<SharedViewModel1>(R.id.flight_connection_graph).value
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUIListeners(view)
        observeViewModel(view)
    }

    private fun configureUIListeners(view: View) {
        view.findViewById<Button>(R.id.btnOk).apply {
            setOnClickListener {
                viewModel.onOkClick()
            }
        }
        view.findViewById<Button>(R.id.btnCancel).apply {
            setOnClickListener {
                viewModel.dismiss()
            }
        }
    }

    private fun observeViewModel(view: View) {
        viewModel.apply {
            dismiss.observe(viewLifecycleOwner) {
                if (it == true) {
                    dismiss()
                }
            }
            okClick.observe(viewLifecycleOwner) {
                if (it == true) {
                    sharedViewModel1.setOkClick(true)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
            parentLayout?.let { view ->
                val behaviour = BottomSheetBehavior.from(view)
                setBehaviourCallBack(bottomSheetDialog)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setBehaviourCallBack(bottomSheetDialog: BottomSheetDialog) {
        val shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setTopLeftCorner(
                CornerFamily.ROUNDED,
                resources.getDimension(R.dimen.common_ui_dialog_radius),
            )
            .setTopRightCorner(
                CornerFamily.ROUNDED,
                resources.getDimension(R.dimen.common_ui_dialog_radius),
            )
            .build()
        val dialogBackground = MaterialShapeDrawable(shapeAppearanceModel)
        dialogBackground.setTint(
            ContextCompat.getColor(
                requireContext(),
                R.color.common_ui_white,
            ),
        )

        bottomSheetDialog.behavior.addBottomSheetCallback(
            object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheet.background = dialogBackground
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            },
        )
    }
}