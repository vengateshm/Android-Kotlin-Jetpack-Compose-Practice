package dev.vengateshm.compose_material3.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

data class FAQItem(val question: String, val answer: String)

@Composable
fun FAQScreen() {
    val faqItems = remember {
        listOf(
            FAQItem(
                question = "1. What is Lorem Ipsum text?",
                answer = "Lorem ipsum is a placeholder text commonly used in the graphic, print and publishing industries for previewing layouts and visual mockups."
            ),
            FAQItem(
                question = "2. What is Lorem Ipsum?",
                answer = "Lorem ipsum is a placeholder text commonly used in the graphic, print and publishing industries for previewing layouts and visual mockups."
            ),
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = "FAQs",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        items(faqItems, key = { it.question }) { faqItem ->
            FAQItem(faqItem = faqItem)
        }
    }
}

@Composable
fun FAQItem(faqItem: FAQItem) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val transition = updateTransition(targetState = isExpanded, label = "transition")

    val arrowIconRotateAngle by transition.animateFloat(label = "arrowIconRotateAngle") { expanded ->
        if (expanded) 180f else 0f
    }

    val faqQuestionTextColor by transition.animateColor(label = "faqQuestionTextColor") { expanded ->
        if (expanded) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onSurface
    }

    val cardContainerColorAlpha by transition.animateFloat(label = "cardContainerColorAlpha") { expanded ->
        if (expanded) 1f else 0.5f
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    isExpanded = isExpanded.not()
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = cardContainerColorAlpha)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(9f),
                    text = faqItem.question,
                    style = MaterialTheme.typography.titleMedium,
                    color = faqQuestionTextColor
                )
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .graphicsLayer {
                            rotationZ = arrowIconRotateAngle
                        },
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Expand Card"
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = faqItem.answer,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}