package dev.vengateshm.my_custom_lints

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement

class AndDroidEntryPointDetector : Detector(), SourceCodeScanner {
    override fun getApplicableUastTypes(): List<Class<out UElement>> =
        listOf(UClass::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler =
        AndroidEntryPointVisitor(context)
}