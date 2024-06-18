package dev.vengateshm.compose_material3.kotest

import dev.vengateshm.compose_material3.third_party_libraries.testing.kotest.Product
import dev.vengateshm.compose_material3.third_party_libraries.testing.kotest.ShoppingCart
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BehaviourSpecTest : BehaviorSpec({
    val p1 = Product(id = "id1", name = "Product1", price = 10.0)
    val p2 = Product(id = "id2", name = "Product2", price = 5.0)
    Given("an empty shopping cart") {
        val shoppingCart = ShoppingCart()
        When("adding a product") {
            shoppingCart.addProduct(p1, 2)
            Then("the cart should contain the product with the specified quantity") {
                shoppingCart.items.size shouldBe 1
                shoppingCart.items[p1] shouldBe 2
            }
        }
        When("adding multiple products") {
            shoppingCart.addProduct(p1, 2)
            shoppingCart.addProduct(p2, 3)
            Then("the cart should contain the product with the specified quantity") {
                shoppingCart.items.size shouldBe 2
                shoppingCart.items[p1] shouldBe 2
                shoppingCart.items[p2] shouldBe 3
            }
        }
    }

    Given("a shopping cart with some products") {
        val cart = ShoppingCart()
        cart.addProduct(p1, 2)
        cart.addProduct(p2, 1)

        When("removing a product with a valid quantity") {
            cart.removeProduct(p1, 1)

            Then("the quantity of the product should be reduced by the removed amount") {
                cart.items.size shouldBe 2
                cart.items[p1] shouldBe 1
            }
        }

        When("removing a product with a quantity exceeding the cart quantity") {
            Then("an exception should be thrown") {
                shouldThrow<IllegalArgumentException> {
                    cart.removeProduct(p2, 2)
                }
            }
        }

        When("removing a product that is not in the cart") {
            Then("an exception should be thrown") {
                shouldThrow<IllegalArgumentException> {
                    cart.removeProduct(Product("id3", "Product 3", 3.0), 1)
                }
            }
        }

        When("removing all of a product") {
            cart.removeProduct(p2, 1)

            Then("the product should be removed from the cart") {
                cart.items.size shouldBe 1
                cart.items.containsKey(p2) shouldBe false
            }
        }

        When("getting the total price") {
            val totalPrice = cart.getTotalPrice()

            Then("the total price should be the sum of the product prices multiplied by their quantities") {
                totalPrice shouldBe (p1.price * 2 + p2.price)
            }
        }

        When("checking out") {
            val totalPrice = cart.checkout()

            Then("the total price should be returned and the cart should be emptied") {
                totalPrice shouldBe (p1.price * 2 + p2.price)
                cart.items.isEmpty() shouldBe true
            }
        }
    }
})