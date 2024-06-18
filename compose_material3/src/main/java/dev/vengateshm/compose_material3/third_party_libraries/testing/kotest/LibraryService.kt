package dev.vengateshm.compose_material3.third_party_libraries.testing.kotest

data class Book(val id: String, val title: String, val author: String)
data class Borrower(val id: String, val name: String)

class LibraryService {
    val books = mutableMapOf<String, Book>()
    val borrowers = mutableMapOf<String, Borrower>()

    fun addBook(book: Book) {
        books[book.id] = book
    }

    fun removeBook(bookId: String) {
        books.remove(bookId)
    }

    fun borrowBook(bookId: String, borrower: Borrower) {
        require(books.containsKey(bookId)) { "Book not found" }
        require(!borrowers.containsKey(borrower.id)) { "Borrower has already borrowed a book" }
        borrowers[bookId] = borrower
    }

    fun returnBook(bookId: String, borrower: Borrower) {
        require(borrowers.containsKey(bookId)) { "Borrower not found or has not borrowed any book" }
        borrowers.remove(bookId)
    }

    fun isBookAvailable(bookId: String) : Boolean{
        return books.containsKey(bookId) && !borrowers.any { it.key == bookId }
    }

    fun listAvailableBooks():List<Book>{
        return books.values.filter { isBookAvailable(it.id) }
    }
}