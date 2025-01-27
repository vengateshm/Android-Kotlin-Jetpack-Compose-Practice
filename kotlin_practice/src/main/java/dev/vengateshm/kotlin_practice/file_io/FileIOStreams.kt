package dev.vengateshm.kotlin_practice.file_io

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

const val INPUT_FILE = "foo.txt"
const val OUTPUT_FILE = "bar.txt"

fun main() {
    val file = File(INPUT_FILE)
    if (!file.exists()) {
        file.createNewFile()
    }

    file.writeText("Its a foo!")
    val inputStream = FileInputStream(INPUT_FILE)
    val bytes = inputStream.readBytes() // crash may happen here and fails to close stream
    inputStream.close()
    println(bytes.contentToString())
    println(bytes.decodeToString())

    FileInputStream(INPUT_FILE)
        .use {
            val bytes = it.readBytes()
            println()
            println(bytes.decodeToString())
        }
    FileInputStream(INPUT_FILE)
        .use {
            var byte = it.read() // 80 MB file 80 million low level calls happen and its slow
            val sb = StringBuilder()
            while (byte != -1) {
                sb.append(byte.toChar())
                byte = it.read()
            }
            println()
            println(sb.toString())
        }

    val outFile = File(OUTPUT_FILE)
    if (!outFile.exists()) {
        outFile.createNewFile()
    }
    FileOutputStream(outFile)
        .use { os ->
            os.fd
            repeat(100) {
                os.write("$it\n".encodeToByteArray())
            }
            os.write("Bar".encodeToByteArray())
        }
}
