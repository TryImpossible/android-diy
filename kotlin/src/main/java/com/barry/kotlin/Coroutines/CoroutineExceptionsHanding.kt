package com.barry.kotlin.Coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.io.IOException

///// 异常的传播
//@OptIn(DelicateCoroutinesApi::class)
//fun main() = runBlocking {
//    val job = GlobalScope.launch { // launch根协程
//        println("Throwing exception from launch")
//        throw IndexOutOfBoundsException() // 我们将在控制台打印Thread.defaultUncaughtException
//    }
//    job.join()
//    println("Joined failed job")
//    val deferred = GlobalScope.async { // async根协程
//        println("Throwing exception from async")
//        throw ArithmeticException() // 没有打印任何东西，依赖用户去调用等待
//    }
//    try {
//        deferred.await()
//        println("Unreached")
//    } catch (e: ArithmeticException) {
//        println("Caught ArithmeticException")
//    }
//}



///// CoroutineExceptionHandler
//@OptIn(DelicateCoroutinesApi::class)
//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler{ _, exception ->
//        println("CoroutineExceptionHandler got $exception")
//    }
//    val job = GlobalScope.launch(handler) { // 根协程，运行在GlobalScope中
//        throw AssertionError()
//    }
//    val deferred = GlobalScope.async(handler) { // 同样是根协程，但使用async代替了launch
//        throw ArithmeticException() // 没有打印任何东西，依赖用户去调用deferred.await()
//    }
//    joinAll(job, deferred)
//}



/// 取消与异常
//fun main() = runBlocking {
//    val job = launch {
//        val child = launch {
//            try {
//                delay(Long.MAX_VALUE)
//            } finally {
//                println("Child is cancelled")
//            }
//        }
//        yield()
//        println("Cancelling child")
//        child.cancel()
//        child.join()
//        yield()
//        println("Parent is not cancelled")
//    }
//    job.join()
//}
//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler {_, exception ->
//        println("CoroutineExceptionHandler got $exception")
//    }
//    val job = GlobalScope.launch(handler) {
//        launch { // 第一个协程
//            try {
//                delay(Long.MAX_VALUE)
//            } finally {
//                withContext(NonCancellable) {
//                    println("Children are cancelled, but exception is not handled until all children terminate")
//                    delay(100)
//                    println("The first child finished its non cancellable block")
//                }
//            }
//        }
//        launch { // 第二个子协程
//            delay(10)
//            println("Second child throws an exception")
//            throw ArithmeticException()
//        }
//    }
//    job.join()
//}



/// 异常聚合
//@OptIn(DelicateCoroutinesApi::class)
//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler{_, exception ->
//        println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
//    }
//    val job = GlobalScope.launch(handler) {
//        launch {
//            try {
//                delay(Long.MAX_VALUE) // 当另一个同级的协程因IOException失败时，它被将取消
//            } finally {
//                throw ArithmeticException() // 第二个异常
//            }
//        }
//        launch {
//            delay(100)
//            throw IOException() //首个异常里拍
//        }
//        delay(Long.MAX_VALUE)
//    }
//    job.join()
//}
//@OptIn(DelicateCoroutinesApi::class)
//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler{
//            _, exception ->
//        println("CoroutineException got $exception")
//    }
//    val job = GlobalScope.launch(handler) {
//        val innerJob = launch { // 该栈内协程都将被取消
//            launch {
//                launch {
//                    throw IOException() // 原始陈普
//                }
//            }
//        }
//        try {
//            innerJob.join()
//        } catch (e: CancellationException) {
//            println("Rethrowing CancellationException with original cause")
//            throw e // 取消异常被重新抛出，但原始 IOException 得到了处理
//        }
//    }
//    job.join()
//}



///// 监督-监督作业
//fun main() = runBlocking {
//    val supervisor = SupervisorJob()
//    with(CoroutineScope(coroutineContext + supervisor)) {
//        // 启动第一个子作业--这个示例将会忽略它的异常（不要在实践中这么做！）
//        val firstChild = launch(CoroutineExceptionHandler{ _, _ -> }) {
//            println("The first child is failing")
//            throw AssertionError("The first child is cancelled")
//        }
//        // 启动第二个子作业
//        val secondChild = launch {
//            firstChild.join()
//            // 取消了第一个子作业且没有传播给第二个子作业
//            println("The first child is cancelled: ${firstChild.isCancelled}, but the second one is still active")
//            try {
//                delay(Long.MAX_VALUE)
//            } finally {
//                // 但是取消了监督的传播
//                println("The second child is cancelled because the supervisor was cancelled")
//            }
//        }
//        // 等待直到第一个子作业失败且执行完成
//        firstChild.join()
//        println("Cancelling the supervisor")
//        supervisor.cancel()
//        secondChild.join()
//    }
//}



///// 监督-监督作用域
//fun main() = runBlocking {
//    try {
//        supervisorScope {
//            val child = launch {
//                try {
//                    println("The child is sleeping")
//                    delay(Long.MAX_VALUE)
//                } finally {
//                    println("The child is cancelled")
//                }
//            }
//            // 使用yield来给我们的子作业一个机会来执行打印
//            yield()
//            println("Throwing an exception from the scope")
//            throw AssertionError()
//        }
//    } catch (e: AssertionError) {
//        println("Caught an assertion error")
//    }
//}



///// 监督-监督协程中的异常
//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler{ _, exception ->
//        println("CoroutineException got $exception")
//    }
//    supervisorScope {
//        val child = launch(handler) {
//            println("The child throws an exception")
//            throw AssertionError()
//        }
//        println("The scope is completing")
//    }
//    println("The scope is completed")
//}