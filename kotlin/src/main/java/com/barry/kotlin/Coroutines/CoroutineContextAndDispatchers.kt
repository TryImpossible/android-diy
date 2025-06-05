package com.barry.kotlin.Coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.asContextElement
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

///// 调试器与线程
//fun main(): Unit = runBlocking {
//    launch { // 运行在父协程的上下文中，即 runBlocking 主协程
//        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Unconfined) { // 不受限的-将工作在主线程中
//        println("Unconfined: I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Default) { // 将会获取默认调度器
//        println("Default: I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新线程
//        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//    }
//}



///// 非受限调度器 vs 受限调度器
//fun main(): Unit = runBlocking {
//    launch(Dispatchers.Unconfined) {  // 非受限的-将和主线程一起工作
//        println("Unconfined: I'm working in thread ${Thread.currentThread().name}")
//        delay(500)
//        println("Unconfined: After delay in thread ${Thread.currentThread().name}")
//    }
//    launch { // 父协程的上下文，主 runBlocking 协程
//        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
//        delay(1000)
//        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
//    }
//}



///// 用日志调试
//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//fun main() = runBlocking {
//    val a = async {
//        log("I'm computing a piece of the answer")
//        6
//    }
//    val b = async {
//        log("I'm computing another piece of the answer")
//        7
//    }
//    log("The answer is ${a.await() * b.await()}")
//}



///// 在不同线程间跳转
//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//fun main() {
//    newSingleThreadContext("Ctx1").use { ctx1 ->
//        newSingleThreadContext("Ctx2").use { ctx2 ->
//            runBlocking(ctx1) {
//                log("Started in ctx1")
//                withContext(ctx2) {
//                    log("Working in ctx2")
//                }
//                log("Back to ctx1")
//            }
//        }
//    }
//}



///// 上下文中的作业
//fun main() = runBlocking {
//    println("My job is ${coroutineContext[Job]}")
//}



///// 子协程
//fun main() = runBlocking {
//    // 启动一个协程来处理某种传入请求(request)
//    val request = launch {
//        // 生成了两个子作业
//        launch(Job()) {
//            println("job1: I run in my own job and execute independently!")
//            delay(1000)
//            println("job1: I am affected by cancellation of the request")
//        }
//        // 另一个则承袭了父协程的上下文
//        launch {
//            delay(100)
//            println("job2: I am a child of the request coroutine")
//            delay(1000)
//            println("job2: I will not execute this line if my parent request is cancelled")
//        }
//    }
//    delay(500)
//    request.cancel() // 取消请求（request）的执行
//    println("main: Who has survived request cancellation?")
//    delay(1000) // 主线程延迟一秒钟来看看发生了什么
//}



///// 父协程的职责
//fun main() = runBlocking {
//    val request = launch {
//        repeat(3) { i -> // 启动少量的作业
//            launch {
//                delay((i + 1) * 200L) // 延迟200毫秒、400毫秒、600毫秒的时间
//                println("Coroutine $i is done")
//            }
//        }
//        println("request: I'm done and I don't explicitly join my children that are still active")
//    }
//    request.join() // 等待请求的完成，包括其所有子协程
//    println("Now processing of the request is complete")
//}



///// 命名协程以用于调试
//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//fun main() = runBlocking {
//    log("Started main coroutine")
//    // 运行两个后台值计算
//    val v1 = async(CoroutineName("v1coroutine")) {
//        delay(500)
//        log("Computing v1")
//        6
//    }
//    val v2 = async(CoroutineName("v2coroutine")) {
//        delay(1000)
//        log("Computing v2")
//        7
//    }
//    log("The answer for v1 * v2 = ${v1.await() * v2.await()}")
//}



///// 组合上下文中的元素
//fun main(): Unit = runBlocking {
//    launch(Dispatchers.Default + CoroutineName("test")) {
//        println("I'm working in thread ${Thread.currentThread().name}")
//    }
//}



///// 协程作用域
//class Activity {
//    private val mainScope = CoroutineScope(Dispatchers.Default)
//
//    fun destroy() {
//        mainScope.cancel()
//    }
//
//    fun doSomething() {
//        // 在示例中启动了10个协程，且每个都工作了不同的时长
//        repeat(10) { i ->
//            mainScope.launch {
//                delay((i + 1) * 200L) // 延迟200毫秒、400毫秒、600毫秒的时间
//                println("Coroutine $i is done")
//            }
//        }
//    }
//}
//fun main() = runBlocking {
//    val activity = Activity()
//    activity.doSomething() // 运行测试函数
//    println("Launched coroutines")
//    delay(500L) // 延迟半秒钟
//    println("Destroying activity!")
//    activity.destroy() // 取消所有的协程
//    delay(1000) // 为了在视觉上确认它们没有工作
//}



///// 线程局部数据
//val threadLocal = ThreadLocal<String>() // 声明线程局部变量
//fun main() = runBlocking {
//    threadLocal.set("main")
//    println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
//    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
//        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
//        yield()
//        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
//    }
//    job.join()
//    println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
//}