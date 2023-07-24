package dev.vengateshm.kotlin_practice.std_library_samples

/*
* THE ADVENTURE OF SHERLOCK HOLMES
* Table of Contents
* --------------------------------
* 1. A Scandal in Bohemia - 1
* 2. The Red-Headed League - 22
* 3. A Case of Identity - 41
* --------------------------------
*
* Copyright 1982
* */

val title = "THE ADVENTURE OF SHERLOCK HOLMES"
val tableOfContents = "Table of Contents"
val copyright = "Copyright 1982"
val divider = "--------------------------------"

val toc = listOf(
    title,
    tableOfContents,
    divider,
    "1. A Scandal in Bohemia - 1",
    "2. The Red-Headed League - 22",
    "3. A Case of Identity - 41",
    divider,
    "",
    copyright,
)

data class Content(val title: String, val pageNumber: Int)


val contents = listOf(
    Content(
        title = "A Scandal in Bohemia", pageNumber = 1
    ),
    Content(
        title = "The Red-Headed League", pageNumber = 22
    ),
    Content(
        title = "A Case of Identity", pageNumber = 41
    ),
)

// 6 list objects created
fun toc1(
    bookTitle: String,
    contents: List<Content>,
    copyright: String?,
): List<String> =
    listOf(
        bookTitle.uppercase(),
        tableOfContents,
        divider,
    ) +
            contents.mapIndexed { index, content ->
                "$index. ${content.title} - ${content.pageNumber}"
            } +
            divider +
            if (copyright != null) {
                listOf(copyright)
            } else {
                emptyList()
            }

// 1 list object created but mutable list created so items can be changed
fun toc2(
    bookTitle: String,
    contents: List<Content>,
    copyright: String?,
): List<String> {
    val result = mutableListOf<String>()
    result.add(bookTitle.uppercase())
    result.add(tableOfContents)
    result.add(divider)
    contents.mapIndexedTo(result) { index, content ->
        "$index. ${content.title} - ${content.pageNumber}"
    }
    result.add(divider)
    if (copyright != null) {
        result.add("")
        result.add(copyright)
    }
    return result
}

fun toc3(
    bookTitle: String,
    contents: List<Content>,
    copyright: String?,
): List<String> =
    buildList {
        add(bookTitle.uppercase())
        add(tableOfContents)
        add(divider)
        contents.mapIndexedTo(this) { index, content ->
            "$index. ${content.title} - ${content.pageNumber}"
        }
        add(divider)
        if (copyright != null) {
            add("")
            add(copyright)
        }
    }

fun main() {
//    toc.forEach(::println)
//    toc1(title, contents, copyright).forEach(::println)
    val result = toc3(title, contents, copyright)
    result.forEach(::println)
    if (result is MutableList) {
        //result[0] = "Foo bar"
    }
    result.forEach(::println)
}