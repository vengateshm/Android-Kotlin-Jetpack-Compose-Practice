package dev.vengateshm.kotlin_practice.std_library_samples

fun main() {

}


class Logger {
    context(BaseRepository)
    fun log(msg: String) {
        println(msg)
    }
}

interface BaseRepository {

}

interface BaseViewModel {

}

class MyRepository : BaseRepository {
    private val logger = Logger()
    fun getData() {
        logger.log("")
    }
}

class MyViewModel : BaseViewModel {
    private val logger = Logger()
    fun getData() {
        // logger.log("") Error
    }
}

class MyView {
    private val logger = Logger()
    fun showData() {
         //logger.log("") Error
    }
}