package dev.vengateshm.xml_kotlin.coroutines

fun logThreadInfo(msg:String){
    println("$msg: Running on thread ${Thread.currentThread().name}")
}