package dev.vengateshm.compose_material3.api_compose.fragments_as_composables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    var onTextClicked: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val context = container?.context
        val textView = AppCompatTextView(context!!)
        val age = arguments?.getInt("id")
        val name = arguments?.getString("name")
        textView.text = "$age. $name"
        textView.setOnClickListener {
            onTextClicked?.invoke()
        }
        return textView
    }
}