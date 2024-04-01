package dev.vengateshm.my_custom_lints

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity

object AndroidEntryPointIssue {
    private const val ID = "AndroidEntryPointImplementationIssue" // Fixed ID
    private const val PRIORITY = 7 // 10 important / severe
    private const val DESCRIPTION = "Use @AndroidEntryPoint before running the activity"
    private const val EXPLANATION = "Activity class not annotated with @AndroidEntryPoint"
    private val CATEGORY = Category.CORRECTNESS
    private val SEVERITY = Severity.INFORMATIONAL

    val ISSUE = Issue.create(
        id = ID,
        briefDescription = DESCRIPTION,
        explanation = EXPLANATION,
        category = CATEGORY, priority = PRIORITY,
        severity = SEVERITY,
        implementation = Implementation(
            AndDroidEntryPointDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )
    )
}