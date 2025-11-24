package dev.vengateshm.kotlin_practice.solid

// Needs modification
class DiscountCalculator {
  fun calculateDiscount(type: String, amount: Double): Double {
    return when (type) {
      "DIWALI" -> amount * 0.1
      "NEWYEAR" -> amount * 0.15
      else -> 0.0
    }
  }
}

// Polymorphism and strategy pattern
interface Discount {
  fun apply(amount: Double): Double
}

class DiwaliDiscount : Discount {
  override fun apply(amount: Double): Double {
    return amount * 0.1
  }
}

class NewYearDiscount : Discount {
  override fun apply(amount: Double): Double {
    return amount * 0.15
  }
}

class DiscountCalculatorNew {
  fun calculate(discount: Discount, amount: Double): Double {
    return discount.apply(amount)
  }
}