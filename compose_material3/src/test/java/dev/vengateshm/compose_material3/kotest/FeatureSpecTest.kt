package dev.vengateshm.compose_material3.kotest

import dev.vengateshm.compose_material3.third_party_libraries.testing.kotest.Book
import dev.vengateshm.compose_material3.third_party_libraries.testing.kotest.Borrower
import dev.vengateshm.compose_material3.third_party_libraries.testing.kotest.LibraryService
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.core.test.config.TestConfig
import io.kotest.matchers.shouldBe

@OptIn(ExperimentalKotest::class)
class FeatureSpecTest : FeatureSpec({
    val libraryService = LibraryService()

    afterTest {
        println(it.b.toString())
    }

    val testConfig = TestConfig(enabled = true, invocations = 7)
    feature("Library Service") {
        scenario("Add and remove books").config(config = testConfig) {
            println("Library Service : $libraryService")
            val book1 = Book(id = "001", title = "Book1", author = "Author1")
            val book2 = Book(id = "002", title = "Book2", author = "Author2")

            libraryService.addBook(book1)
            libraryService.addBook(book2)

            libraryService.listAvailableBooks() shouldBe listOf(book1, book2)
            libraryService.removeBook(book1.id)

            libraryService.listAvailableBooks() shouldBe listOf(book2)
        }

        scenario("Borrow and return book") {
            println("Library Service : $libraryService")
            val book3 = Book(id = "003", title = "Book3", author = "Author3")
            val borrower = Borrower(id = "100", "Borrower1")

            libraryService.addBook(book3)

            libraryService.listAvailableBooks() shouldBe listOf(book3)

            libraryService.borrowBook(book3.id, borrower)

            libraryService.isBookAvailable(book3.id) shouldBe false
            libraryService.listAvailableBooks() shouldBe emptyList()

            libraryService.returnBook(book3.id, borrower)

            libraryService.isBookAvailable(book3.id) shouldBe true
        }
    }
})