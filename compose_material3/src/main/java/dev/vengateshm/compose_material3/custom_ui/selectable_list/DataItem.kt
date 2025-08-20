package dev.vengateshm.compose_material3.custom_ui.selectable_list

import java.util.Locale

data class DataItem(
  val id: String,
  val title: String,
  val interestRate: Double,
  val monthlyPaymentInCents: Long
) {

  val monthlyPaymentFormatted: String
    get() = String.format(Locale.getDefault(),"$%.2f", monthlyPaymentInCents/100.0)
}
