package dev.vengateshm.my_custom_lints

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UClass

class AndroidEntryPointVisitor(private val context: JavaContext) : UElementHandler() {

    private val superClassQualifiedNameFragment =
        "androidx.appcompat.app.AppCompatActivity"

    override fun visitClass(node: UClass) {
        if (node.javaPsi.superClass?.qualifiedName == superClassQualifiedNameFragment &&
            !node.hasAnnotation("dagger.hilt.android.AndroidEntryPoint")
        ) {
            reportIssue(node)
        }
    }

    private fun reportIssue(node: UClass) {
        context.report(
            AndroidEntryPointIssue.ISSUE,
            node,
            context.getNameLocation(node),
            "This class is not annotated with @AndroidEntryPoint. " +
                    "It is recommended to add @AndroidEntryPoint to your activity class.")
    }
}