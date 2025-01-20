package dev.vengateshm.kotlin_practice.file_io

import java.io.File

fun main() {
//    val file = File("build.gradle.kts")
//    println(file.exists())
    // ./  = current directory
    // ../ = parent directory
//    val folder = File("hello/world")
    val folder = File("../Android-Kotlin-Jetpack-Compose-Practice/hello/world")
    if (!folder.exists()) {
        folder.mkdirs()
    }
    val file = File(folder, "hello.txt")
    file.createNewFile()
    file.writeText("Hello world!")

    val rootFolder = File(".")
//    rootFolder.listFiles().forEach {
//        println(it.name)
//    }
    printFilesRecursively(rootFolder)
}

fun printFilesRecursively(folder: File, indentationLevel: Int = 0) {
    folder.listFiles()?.forEach { child ->
        var line = buildString {
            repeat(indentationLevel) {
                append("  ")
            }
            append("- ${child.name}")
        }
        println(line)
        if (child.isDirectory) {
            printFilesRecursively(child, indentationLevel + 1)
        }
    }
}