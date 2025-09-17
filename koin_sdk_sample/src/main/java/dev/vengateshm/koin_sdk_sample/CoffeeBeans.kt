package dev.vengateshm.koin_sdk_sample

enum class RoastLevel {
  LIGHT, MEDIUM, DARK
}

class CoffeeBeans {
  lateinit var origin: String
    private set // External classes can read but not directly set after initialization

  var weightInGrams: Double = 0.0
    private set

  var roastLevel: RoastLevel = RoastLevel.MEDIUM
    private set

  private var isGround: Boolean = false

  fun initialize(
    origin: String,
    weightInGrams: Double,
    roastLevel: RoastLevel = RoastLevel.MEDIUM,
  ) {
    require(origin.isNotBlank()) { "Origin cannot be blank" }
    require(weightInGrams > 0) { "Weight must be positive" }
    this.origin = origin
    this.weightInGrams = weightInGrams
    this.roastLevel = roastLevel
    this.isGround = false // Reset ground state on new initialization
  }

  fun setOrigin(origin: String) {
    require(origin.isNotBlank()) { "Origin cannot be blank" }
    this.origin = origin
  }

  fun setWeightInGrams(weight: Double) {
    require(weight > 0) { "Weight must be positive" }
    this.weightInGrams = weight
  }

  fun setRoastLevel(roast: RoastLevel) {
    this.roastLevel = roast
  }

  fun grind() {
    if (!::origin.isInitialized) {
      println("CoffeeBeans not initialized. Please call initialize() first.")
      return
    }
    if (!isGround) {
      println("Grinding $weightInGrams grams of $origin beans...")
      isGround = true
    } else {
      println("$origin beans are already ground.")
    }
  }

  fun isGround(): Boolean {
    return isGround
  }

  fun brew(waterAmountMl: Double): String {
    if (!::origin.isInitialized) {
      return "CoffeeBeans not initialized. Please call initialize() first."
    }
    if (!isGround) {
      return "Cannot brew unground beans. Please grind them first for $origin."
    }
    if (waterAmountMl <= 0) {
      return "Water amount must be positive."
    }
    val strength = weightInGrams / waterAmountMl
    return "Brewing $weightInGrams grams of $roastLevel roast $origin beans with $waterAmountMl ml of water. Strength: $strength"
  }

  override fun toString(): String {
    if (!::origin.isInitialized) {
      return "CoffeeBeans (Uninitialized)"
    }
    return "CoffeeBeans(origin='$origin', weightInGrams=$weightInGrams, roastLevel=$roastLevel, isGround=$isGround)"
  }
}
