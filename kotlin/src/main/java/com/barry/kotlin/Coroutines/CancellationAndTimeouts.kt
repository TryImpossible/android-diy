package com.barry.kotlin.Coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

//fun main() = runBlocking {
//    val job = launch {
//        repeat(1000) { i ->
//            println("job: I'm sleeping $i")
//            delay(500L)
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancel() // cancels the job
//    job.join() // waits for job's completion
//    println("main: Now I can quit")
//}

//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (i < 5) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++}")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L)
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        repeat(5){ i ->
//            try {
//                // print a message twice a second
//                println("job: I'm sleeping $i")
//                delay(500L)
//            } catch (e: Exception) {
//                // log the exception
//                println(e)
//            }
//        }
//    }
//    delay(1300L)
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (isActive) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++}...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L)
//    println("main: I'm third of waiting!")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

//fun main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            println("job: I'm running finally")
//        }
//    }
//    delay(1300L)
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

//fun main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            withContext(NonCancellable) {
//                println("job: I'm running finally")
//                delay(500L)
//                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
//            }
//        }
//    }
//    delay(1300L)
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

//fun main() = runBlocking {
//    try {
//        withTimeout(1300L) {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        }
//    } catch (e: TimeoutCancellationException) {
//        println(e)
//    }
//}

//fun main() = runBlocking {
//    val result = withTimeoutOrNull(1300L) {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//        "Done"
//    }
//    println("Result is $result")
//}

var acquired = 0

class Resource {
    init {
        acquired++
    }

    fun close() {
        acquired--
    }
}

//fun main() {
//    runBlocking {
//        repeat(10_000) {
//            launch {
//                val resource = withTimeout(60) {
//                    delay(50)
//                    Resource()
//                }
//                resource.close()
//            }
//        }
//    }
//    println(acquired)
//}

fun main() {
    runBlocking {
        repeat(10_000) {
            launch {
                var resource: Resource? = null
                try {
                    withTimeout(60) {
                        delay(50)
                        resource = Resource()
                    }
                } finally {
                    resource?.close()
                }
            }
        }
    }
    println(acquired)
}