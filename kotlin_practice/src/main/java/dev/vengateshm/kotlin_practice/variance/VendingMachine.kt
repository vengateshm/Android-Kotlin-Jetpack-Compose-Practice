package dev.vengateshm.kotlin_practice.variance

open class Product

open class Snack : Product()
class CandyBar : Snack()
class TrailMix : Snack()

open class Toy : Product()
class ActionFigure : Toy()
class BouncyBall : Toy()

open class Money
open class Coin : Money()
class Dime : Coin()
class Quarter : Coin()

interface VendingMachine {
    fun purchase(coin: Coin): Snack
}

// Valid if function return type replaced with sub type
class VendingMachineA : VendingMachine {
    override fun purchase(coin: Coin): CandyBar = CandyBar()
}

// Not valid as function return type not a sub type of Snack
class VendingMachineB : VendingMachine {
    //override fun purchase(coin: Coin): Product = Snack()
    override fun purchase(coin: Coin): Snack = Snack()
}

class VendingMachineC : VendingMachine {
    // Invalid
    //override fun purchase(coin: Quarter): Snack = Snack()
    // Invalid
    //override fun purchase(coin: Money): Snack = Snack()
    override fun purchase(coin: Coin): Snack = Snack()
}

// Contravariance
interface CoinVendingMachine {
    val purchase: (Coin) -> Snack
}

class SimpleCoinVendingMachine : CoinVendingMachine {
    // Valid
    override val purchase: (Money) -> Snack = { CandyBar() }
}

interface ProductVendingMachine<in T, out R> {
    val purchase: (T) -> R
}

class ProductVendingMachine1 : ProductVendingMachine<Coin, Snack> {
    override val purchase: (Coin) -> Snack = { TrailMix() }
}

class ProductVendingMachine2 : ProductVendingMachine<Money, CandyBar> {
    override val purchase: (Money) -> CandyBar = { CandyBar() }
}