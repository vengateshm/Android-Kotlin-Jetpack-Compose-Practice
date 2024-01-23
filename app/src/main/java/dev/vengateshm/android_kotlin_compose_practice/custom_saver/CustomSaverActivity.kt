package dev.vengateshm.android_kotlin_compose_practice.custom_saver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson

class CustomSaverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BookItem()
            }
        }
    }
}

// Usage
@Composable
fun BookItem() {
    val book =
        rememberSaveable(stateSaver = BookSaverMapApproach) {
            mutableStateOf(Book("A123", "Title 1", 4.6))
        }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = book.value.isbn)
        Text(text = book.value.title)
        Text(text = book.value.rating.toString())
    }
}

// Custom object
data class Book(val isbn: String, val title: String, val rating: Double)

// Custom saver implementing Saver interface
// Saving data into map
object BookSaverMapApproach : Saver<Book, Map<String, Any>> {
    override fun restore(value: Map<String, Any>): Book {
        return Book(
            isbn = value["isbn"] as String,
            title = value["title"] as String,
            rating = value["rating"] as Double,
        )
    }

    override fun SaverScope.save(value: Book): Map<String, Any> {
        return mapOf(
            "isbn" to value.isbn,
            "title" to value.title,
            "rating" to value.rating,
        )
    }
}

// Another approach
// Custom saver implementing Saver interface
// Saving data as string using gson
object BookSaverGsonApproach : Saver<Book, String> {
    override fun restore(value: String): Book? {
        return Gson().fromJson(value, Book::class.java)
    }

    override fun SaverScope.save(value: Book): String {
        return Gson().toJson(value)
    }
}

@Preview
@Composable
fun BookItemPreview() {
    BookSaverGsonApproach
    BookItem()
}

object BookSaver {
    val listSaver = listSaver(
        save = { book: Book ->
            listOf(
                book.isbn,
                book.title,
                book.rating
            )
        },
        restore = { list ->
            Book(
                isbn = list[0] as String,
                title = list[1] as String,
                rating = list[2] as Double
            )
        }
    )

    val mapSaver = mapSaver(
        save = { book: Book ->
            mapOf(
                "isbn" to book.isbn,
                "title" to book.title,
                "rating" to book.rating
            )
        },
        restore = { map ->
            Book(
                isbn = map["isbn"] as String,
                title = map["title"] as String,
                rating = map["rating"] as Double
            )
        }
    )

    val anotherSaver = object : Saver<Book, List<Any>> {
        override fun restore(value: List<Any>): Book? {
            return Book(
                isbn = value[0] as String,
                title = value[1] as String,
                rating = value[2] as Double
            )
        }

        override fun SaverScope.save(value: Book): List<Any>? {
            return listOf(
                value.isbn,
                value.title,
                value.rating
            )
        }
    }
}