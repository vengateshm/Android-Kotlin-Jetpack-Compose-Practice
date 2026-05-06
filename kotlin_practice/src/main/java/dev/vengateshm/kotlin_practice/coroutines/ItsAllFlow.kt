package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun readByLine(file: File) = channelFlow {
  val reader = async(Dispatchers.IO) {
    BufferedReader(FileReader(file))
  }

  val file = reader.await()

  val job = launch(Dispatchers.IO) {
    file.use { reader ->
      while (true) {
        val line = reader.readLine() ?: break
        send(line)
      }
    }
    close()
  }

  awaitClose {
    println("awaitClose called")
    job.cancel()
    reader.cancel()
  }
}

fun main() {
  runBlocking {
    readByLine(File("hello.txt"))
      .onEach {
        println(it)
        if (it == "STOP")
          cancel()
      }
      .collect()
  }
}