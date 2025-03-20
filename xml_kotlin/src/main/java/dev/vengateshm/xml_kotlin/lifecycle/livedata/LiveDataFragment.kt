package dev.vengateshm.xml_kotlin.lifecycle.livedata

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class LiveDataFragment : Fragment() {

    private lateinit var textView: TextView

    private val viewModel: LiveDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = linearLayout(container!!.context) {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )
        }.also {
            it.addView(
                textView(container.context) {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                    )
                    textSize = 18f
                    gravity = Gravity.CENTER
                    setTextColor(Color.GRAY)
                }.apply {
                    textView = this
                },
            )
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userData.observe(viewLifecycleOwner) {
            textView.text = it.orEmpty()
        }
    }
}

fun linearLayout(context: Context, block: LinearLayout.() -> Unit): LinearLayout {
    val ll = LinearLayout(context)
    ll.apply(block)
    return ll
}

fun textView(context: Context, block: TextView.() -> Unit): TextView {
    val tv = TextView(context)
    tv.apply(block)
    return tv
}