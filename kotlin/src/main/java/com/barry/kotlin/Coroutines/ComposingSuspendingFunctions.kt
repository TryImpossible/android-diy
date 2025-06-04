package com.barry.kotlin.Coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    return 29
}

// somethingUsefulOneAsync 函数的返回值类型是 Deferred
@OptIn(DelicateCoroutinesApi::class)
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// somethingUsefulTwoAsync 函数的返回值类型是 Deferred
@OptIn(DelicateCoroutinesApi::class)
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

///// 默认顺序调用
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = doSomethingUsefulOne()
//        val two = doSomethingUsefulTwo()
//        println("The answer is ${one + two}")
//    }
//    println("Completed in $time ms")
//}


///// 使用async并发
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = async { doSomethingUsefulOne() }
//        val two = async { doSomethingUsefulTwo() }
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}


///// 惰性启动的 async
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
//        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
//        // 执行一些计算
//        one.start() // 启动第一个
//        two.start() // 启动第二个
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}


///// async风格的函数
//// 注意，在这个示例中我们在`main`函数的左边没有加上`runBlocking`
//fun main() {
//    val time = measureTimeMillis {
//        // 我们可以在协程外面启动异步执行
//        val one = somethingUsefulOneAsync()
//        val two = somethingUsefulTwoAsync()
//        // 但是等待结果必须调用其它的挂起或者阻塞
//        // 当我们等待结果的时候，这里我们使用`runBlocking { ... }`来阻塞主线程
//        runBlocking {
//            println("The answer is ${one.await() + two.await()}")
//        }
//    }
//    println("Completed in $time ms")
//}

/// 使用 async 的结构化并发
fun main(): Unit = runBlocking {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")

    try {
        failedConcurrentSum()
    } catch (e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // 模拟一个长时间的运算
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}