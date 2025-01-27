package dev.vengateshm.kotlin_practice.file_io

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.system.measureTimeMillis

const val INPUT_FILE_BUFF = "foo_buff.txt"
const val OUTPUT_FILE_BUFF = "bar_buff.txt"

fun main() {
    val outFile = File(OUTPUT_FILE_BUFF)
    if (!outFile.exists()) {
        outFile.createNewFile()
    }
    FileOutputStream(outFile)
        .use { os ->
            os.fd
            repeat(10_000_000) {
                os.write("$it\n".encodeToByteArray())
            }
        }
    val sb1 = StringBuilder()
    val time1 = measureTimeMillis {
        FileInputStream(OUTPUT_FILE_BUFF)
            .use {
                var byte = it.read() // 80 MB file 80 million low level calls happen and its slow
                while (byte != -1) {
                    sb1.append(byte.toChar())
                    byte = it.read()
                }
            }
    }
    val sb2 = StringBuilder()
    val time2 = measureTimeMillis {
        FileInputStream(OUTPUT_FILE_BUFF)
            .bufferedReader()
            .use { reader ->
                var byte = reader.read()
                while (byte != -1) {
                    sb2.append(byte.toChar())
                    byte = reader.read()
                }
            }
    }
    println("Time taken 1: $time1 ms.")
    println("Time taken 2: $time2 ms.")
}