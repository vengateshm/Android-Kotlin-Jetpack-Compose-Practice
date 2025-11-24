package dev.vengateshm.kotlin_practice.design_patterns

interface Pizza {
  fun bake()
}

interface Burger {
  fun prepare()
}

class OrientalBurger : Burger {
  override fun prepare() {

  }
}

class ClassicBurger : Burger {
  override fun prepare() {

  }
}

class OrientalPizza : Pizza {
  override fun bake() {

  }
}

class ClassicPizza : Pizza {
  override fun bake() {

  }
}

abstract class Restaurant {
  protected abstract fun createPizza(): Pizza
  protected abstract fun createBurger(): Burger
}

class OrientalRestaurant : Restaurant() {
  public override fun createPizza(): Pizza {
    return OrientalPizza()
  }

  public override fun createBurger(): Burger {
    return OrientalBurger()
  }
}

class ClassicRestaurant : Restaurant() {
  public override fun createPizza(): Pizza {
    return ClassicPizza()
  }

  public override fun createBurger(): Burger {
    return ClassicBurger()
  }
}

fun main() {
  val orientalRestaurant = OrientalRestaurant()
  val classicRestaurant = ClassicRestaurant()

  val orientalPizza = orientalRestaurant.createPizza()
  val orientalBurger = orientalRestaurant.createBurger()

  val classicPizza = classicRestaurant.createPizza()
  val classicBurger = classicRestaurant.createBurger()
}