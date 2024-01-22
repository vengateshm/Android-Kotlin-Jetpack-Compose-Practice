package dev.vengateshm.android_kotlin_compose_practice.text_apis

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun TermsAcknowledgeText(
    annotatedString: AnnotatedString = getTermsAcknowledgeString(),
    onPrivacyClicked: () -> Unit,
    onPolicyClicked: () -> Unit,
) {
    ClickableText(text = annotatedString) { offset ->
        annotatedString.getStringAnnotations(offset, offset).forEach { range ->
            when (range.tag) {
                "privacy" -> {
                    onPrivacyClicked()
                }

                "policy" -> {
                    onPolicyClicked()
                }
            }
        }
    }
}

// Create your annotated string
fun getTermsAcknowledgeString() =
    buildAnnotatedString {
        append("I agree to ")
        pushStringAnnotation(tag = "privacy", "privacy")
        withStyle(SpanStyle(color = Color.Blue)) {
            append("privacy")
        }
        pop()
        append(" and ")
        pushStringAnnotation(tag = "policy", "privacy")
        withStyle(SpanStyle(color = Color.Blue)) {
            append("policy")
        }
        pop()
        append(" terms.")
    }
