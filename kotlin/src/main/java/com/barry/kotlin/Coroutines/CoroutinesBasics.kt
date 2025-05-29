package com.barry.kotlin.Coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() = runBlocking { // this: CoroutineScope
//    launch { doWorld() }
//    println("Hello") // main coroutine continues while a previous one is delayed
//}
//
//suspend fun doWorld() {
//    delay(1000L)
//    println("World!")
//}



//fun main() = runBlocking {
//    doWorld()
//}
//
//suspend fun doWorld() = coroutineScope {
//    launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello")
//}



//fun main() = runBlocking {
//    doWorld()
//    println("Done")
//}
//
//suspend fun doWorld() = coroutineScope {
//    launch {
//        delay(2000L)
//        println("World 2")
//    }
//    launch {
//        delay(1000L)
//        println("World 1")
//    }
//    println("Hello")
//}



//fun main() = runBlocking {
//    val job = launch {
//        delay(1000L)
//        println("World")
//    }
//    println("Hello")
//    job.join()
//    println("Done")
//}



fun main() = runBlocking {
    repeat(50_000) {
        launch {
            delay(5000L)
            print(".")
        }
    }
}