package dev.vengateshm.kotlin_practice.function_types

val regex = "\\s+".toRegex()
val text = "Once upon a time there lived a monk"

fun countWords(text: String) = text.trim().split(regex).size
fun String.wordCount() = this.split(regex).size

class Document(var text: String) {
    val length
        get() = text.length
}

val book = Document(text = text)

fun main() {
    println(countWords(text))
    println(text.wordCount())

    val functionWithoutReceiver: (String) -> Int = ::countWords
    println(functionWithoutReceiver.invoke(text))

    val functionWithReceiver: String.() -> Int = String::wordCount
    println(text.functionWithReceiver())
    println(functionWithReceiver(text))

    val functionWithReceiverObject = text::wordCount // Bound function reference
    println(functionWithReceiverObject())

    val getStory: (Document) -> String = Document::text::get
    val getLength: (Document) -> Int = Document::length::get
    val setStory: (Document, String) -> Unit = Document::text::set

    val getStory1: () -> String = book::text::get
    val getLength1: () -> Int = book::length::get
    val setStory1: (String) -> Unit = book::text::set
    val setStory2: String.() -> Unit = book::text::set
    setStory1("silence")
    "silence".setStory2()

    val history = History(book.text, ::println)
    history.append("Once upon a time there lived a monk")
    history.append("Once upon a time there lived a saint")
    history.back()
    println(book.text)
}

fun process(string: String, customTransform: String.() -> String): String {
    return string
        .lowercase()
        .customTransform()
        .replace(".", "")
}

class History<T>(initial: T, private val apply: T.() -> Unit) {
    private val items = mutableListOf<T>().apply { add(initial) }.apply { initial.apply() }
    private var index = 0
        get() = field
        set(value) {
            field = value.coerceIn(items.indices)
        }

    fun append(value: T) {
        items.retainAll(items.take(index + 1))
        items.add(value)
        index++
        value.apply()
    }

    fun back() = items[--index].apply()
    fun forward() = items[++index].apply()
}