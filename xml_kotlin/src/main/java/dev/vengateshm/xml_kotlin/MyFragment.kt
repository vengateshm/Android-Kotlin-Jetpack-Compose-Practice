package dev.vengateshm.xml_kotlin

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTextView = view.findViewById<TextView>(R.id.tvTextView)
        val htmlText =
            "Click <a href=\"https://www.google.com\" target=\"_blank\">here</a> to visit Example.com"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvTextView.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
        } else {
            tvTextView.text = Html.fromHtml(htmlText)
        }
        tvTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}