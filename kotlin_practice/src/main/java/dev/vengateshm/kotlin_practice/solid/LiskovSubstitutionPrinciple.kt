package dev.vengateshm.kotlin_practice.solid

interface Bird {
  fun fly()
}

class Parrot : Bird {
  override fun fly() {
    println("Parrot is flying...")
  }

}

class Ostrich : Bird {
  override fun fly() {
    throw UnsupportedOperationException("Ostrich can't fly")
  }
}

fun main() {
  letsFly(Parrot())
//  letsFly(Ostrich()) // Throws exception

  letsFly(Myna())
  letsFly(OstrichV1())
}

fun letsFly(bird: Bird) {
  bird.fly()
}

// LSV Fix
interface BirdV1 {}
interface FlyingBird : BirdV1 {
  fun fly()
}

class Myna : FlyingBird {
  override fun fly() {
    println("Myna is flying...")
  }
}

class OstrichV1 : BirdV1

fun letsFly(bird: BirdV1) {
  println("$bird")
}