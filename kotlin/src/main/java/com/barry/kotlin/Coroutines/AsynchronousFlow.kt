package com.barry.kotlin.Coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.system.measureTimeMillis

///// 表示多个值
//fun simple(): List<Int> = listOf(1, 2, 3)
//fun main() {
//    simple().forEach{ value -> println(value) }
//}


///// 序列
//fun simple(): Sequence<Int> = sequence<Int> { // 序列构建器
//    for (i in 1..3) {
//        Thread.sleep(100) // 假装我们正在计算
//        yield(i) // 产生下一个值
//    }
//}
//fun main() {
//    simple().forEach { value -> println(value) }
//}


///// 挂起函数
//suspend fun simple(): List<Int> {
//    delay(1000) // 假装我们在这里做了一些异步的事情
//    return listOf(1, 2, 3)
//}
//fun main() = runBlocking {
//    simple().forEach { value -> println(value) }
//}


///// 流
//fun simple(): Flow<Int> = flow { // 流构建器
//    for (i in 1..3) {
//        delay(100) // 假装我们在这里做了一些有用的事情
//        emit(i) // 发送下一个值
//    }
//}
//fun main() = runBlocking {
//    // 启动并必的协程以验证主线程并未阻塞
//    launch {
//        for (k in 1..3) {
//            println("I'm not blocked $k")
//            delay(100)
//        }
//    }
//    // 收集这个流
//    simple().collect { value -> println(value) }
//}


///// 流是冷的
//fun simple(): Flow<Int> = flow {
//    println("Flow started")
//    for (i in 1..3) {
//        delay(100)
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//    println("Calling simple function...")
//    val flow = simple()
//    println("Calling collect...")
//    flow.collect { value -> println(value) }
//    println("Calling collect again...")
//    flow.collect { value -> println(value) }
//}


///// 流取消基础
//fun simple(): Flow<Int> = flow {
//    for (i in 1..3) {
//        delay(100)
//        println("Emitting $i")
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//    withTimeoutOrNull(250) { // 在250毫秒后超时
//        simple().collect { value -> println(value) }
//    }
//    println("Done")
//}


///// 流构建器
//fun main() = runBlocking {
//    (1..3).asFlow().collect { value -> println(value) }
//}


///// 过渡流操作符
//suspend fun performRequest(request: Int): String {
//    delay(1000) // 模仿长时间运行的异步工作
//    return "response $request"
//}
//fun main() = runBlocking {
//    (1..3).asFlow()
//        .map { request -> performRequest(request) }
//        .collect { response -> println(response) }
//}


///// 转换操作符
//suspend fun performRequest(request: Int): String {
//    delay(1000) // 模仿长时间运行的异步任务
//    return "response $request"
//}
//fun main() = runBlocking {
//    (1..3).asFlow()
//        .transform { request ->
//            emit("Marking request $request")
//            emit(performRequest(request))
//        }
//        .collect{ response -> println(response) }
//}


///// 限长操作符
//fun numbers(): Flow<Int> = flow {
//    try {
//        emit(1)
//        emit(2)
//        println("This line will not execute")
//        emit(3)
//    } finally {
//        println("Finally in numbers")
//    }
//}
//fun main() = runBlocking {
//    numbers().take(2).collect { value -> println(value) }
//}


///// 末端流操作符
//fun main() = runBlocking {
//    val sum = (1..5).asFlow()
//        .map { it * it } // 数字1 至 5的平方
//        .reduce{ a, b -> a + b } // 求和（末端操作符）
//    println(sum)
//}


///// 流是连续的
//fun main() = runBlocking {
//    (1..5).asFlow()
//        .filter {
//            println("Filter $it")
//            it % 2 == 0
//        }
//        .map {
//            println("Map $it")
//            "string $it"
//        }.collect {
//            println("Collect $it")
//        }
//}


///// 流上下文
//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//fun simple(): Flow<Int> = flow {
//    log("Started simple flow")
//    for (i in 1..3) {
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//    simple().collect{ log("Collect $it") }
//}


///// 使用withContext的常见陷阱
//fun simple(): Flow<Int> = flow {
//    // 在流构建器中更改消耗CPU代码的上下文的错误方式
//    kotlinx.coroutines.withContext(Dispatchers.Default) {
//        for (i in 1..3) {
//            Thread.sleep(100) // 假装我们以消耗CPU的方式进行计算
//            emit(i) // 发射下一值
//        }
//    }
//}
//fun main() = runBlocking {
//    simple().collect { value -> println(value) }
//}


///// flowOn操作符
//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//fun simple(): Flow<Int> = flow {
//    for (i in 1..3) {
//        Thread.sleep(100) /// 假装我们以消耗CPU的方式进行计算
//        log("Emitting $i")
//        emit(i) // 发射下一个值
//    }
//}.flowOn(Dispatchers.Default) // 在流构建器中改变消耗CPU代码上下文的正确方式
//fun main() = runBlocking {
//    simple().collect { value ->
//        log("Collected $value")
//    }
//}


///// 缓冲
//fun simple(): Flow<Int> = flow {
//    for (i in 1..3) {
//        delay(100) // 假装我们异步等待了100毫秒
//        emit(i) // 发射下一个值
//    }
//}
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        simple()
//            .buffer() // 缓冲发射项，无需等待
//            .collect { value ->
//                delay(300) // 假装我们花费 300 毫秒来处理它
//                println(value)
//        }
//    }
//    println("Collected in $time ms")
//}



///// 合并
//fun simple(): Flow<Int> = flow {
//    for(i in 1..3) {
//        delay(100) // 假装我们异步等待了100毫秒
//        emit(i) // 发射下一个值
//    }
//}
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        simple()
//            .conflate() // 合并发射项，不对每个值进行处理
//            .collect { value ->
//                delay(300) // 假装我们花费 300 毫秒来处理它
//                println(value)
//            }
//    }
//    println("Collected in $time ms")
//}



///// 处理最新值
//fun simple(): Flow<Int> = flow {
//    for(i in 1..3) {
//        delay(100) // 假装我们异步等待了100毫秒
//        emit(i) // 发射下一个值
//    }
//}
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        simple()
//            .collectLatest { value -> // 取消并重新发射最后一个值
//                println("Collecting $value")
//                delay(300) // 假装我们花费300毫秒来处理它
//                println("Done $value")
//            }
//    }
//    println("Collected in $time ms")
//}



///// 组合多个流
//fun main() = runBlocking {
//    val nums = (1..3).asFlow() // 数字1..3
//    val strs = flowOf("one", "two", "three") // 字符串
//    nums.zip(strs) { a, b -> "$a -> $b" } // 组合单个字符串
//        .collect{ println(it) } // 收集并打印
//}



///// 组合（Combine)
//fun main() = runBlocking {
//    val nums = (1..3).asFlow().onEach { delay(300) } // 发射数字 1..3，间隔 300 毫秒
//    val strs = flowOf("one", "two", "three").onEach { delay(400) } // 每400毫秒发射一次字符串
//    val startTime = System.currentTimeMillis() // 记录开始的时间
//    nums.combine(strs) { a, b -> "$a -> $b"} // 使用combine组合单个字符串
//        .collect{ value -> // 收集并打印
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//        }
//}



///// 展平流-flatMapConcat
//fun requestFlow(i: Int): Flow<String> = flow {
//    emit("$i: First")
//    delay(500) // 等待500毫秒
//    emit("$i: Second")
//}
//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis() // 记录开始时间
//    (1..3).asFlow().onEach { delay(100) } // 每100毫秒发射一个数字
//        .flatMapConcat { requestFlow(it) }
//        .collect{ value -> //收集并打印
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//         }
//}



///// 展平流-flatMapMerge
//fun requestFlow(i: Int): Flow<String> = flow {
//    emit("$i: First")
//    delay(500) // 等待500毫秒
//    emit("$i: Second")
//}
//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis() // 记录开始时间
//    (1..3).asFlow().onEach { delay(100) } // 每100毫秒发射一个数字
//        .flatMapMerge { requestFlow(it) }
//        .collect{ value -> //收集并打印
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//        }
//}



///// 展平流-flatMapLatest
//fun requestFlow(i: Int): Flow<String> = flow {
//    emit("$i: First")
//    delay(500) // 等待500毫秒
//    emit("$i: Second")
//}
//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis() // 记录开始时间
//    (1..3).asFlow().onEach { delay(100) } // 每100毫秒发射一个数字
//        .flatMapLatest { requestFlow(it) }
//        .collect{ value -> //收集并打印
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//        }
//}



///// 流异常-收集器try与catch
//fun simple(): Flow<Int> = flow {
//    for(i in 1..3) {
//        println("Emitting $i")
//        emit(i) // 发射下一个值
//    }
//}
//fun main() = runBlocking {
//    try {
//        simple().collect{ value ->
//            println(value)
//            check(value <= 1) { "Collected $value" }
//        }
//    } catch (e: Throwable) {
//        println("Caught $e")
//    }
//}



///// 流异常-一切都已捕获
//fun simple(): Flow<String> = flow {
//    for(i in 1..3) {
//        println("Emitting $i")
//        emit(i) // 发射下一个值
//    }
//}.map { value ->
//    check(value <= 1) { "Crashed on $value" }
//    "string $value"
//}
//fun main() = runBlocking {
//    try {
//        simple().collect{ value -> println(value) }
//    } catch (e: Throwable) {
//        println("Caught $e")
//    }
//}



///// 流异常-异常透明性
//fun simple(): Flow<String> = flow {
//    for(i in 1..3) {
//        println("Emitting $i")
//        emit(i) // 发射下一个值
//    }
//}.map { value ->
//    check(value <= 1) { "Crashed on $value" }
//    "string $value"
//}
//fun main() = runBlocking {
//    simple()
//        .catch { e -> emit("Caught $e") } // 发射一个异常
//        .collect{ value -> println(value) }
//}



///// 流异常-透明捕获
//fun simple(): Flow<Int> = flow {
//    for(i in 1..3) {
//        println("Emitting $i")
//        emit(i) // 发射下一个值
//    }
//}
//fun main() = runBlocking {
//    simple()
//        .catch { e -> println(("Caught $e")) } // 不会捕获下游异常
//        .collect{ value ->
//            check(value <= 1) { "Collected $value" }
//            println(value)
//        }
//}



///// 流异常-声明式捕获
//fun simple(): Flow<Int> = flow {
//    for(i in 1..3) {
//        println("Emitting $i")
//        emit(i) // 发射下一个值
//    }
//}
//fun main() = runBlocking {
//    simple()
//        .onEach { value ->
//            check(value <= 1) { "Collected $value" }
//            println(value)
//        }
//        .catch { e -> println(("Caught $e")) }
//        .collect()
//}



///// 流完成-命令式finally块
//fun simple(): Flow<Int> = (1..3).asFlow()
//fun main() = runBlocking {
//    try {
//        simple().collect{ value -> println(value) }
//    } finally {
//        println("Done")
//    }
//}



///// 流完成-声明式处理
//fun simple(): Flow<Int> = (1..3).asFlow()
//fun simple2(): Flow<Int> = flow {
//    emit(1)
//    throw RuntimeException()
//}
//fun main() = runBlocking {
//    simple()
//        .onCompletion { println("Done") }
//        .collect{ value -> println(value) }
//
//
//    simple2()
//        .onCompletion { cause -> if (cause != null) println("Flow completed exceptionally") }
//        .catch { cause -> println("Caught exception") }
//        .collect{ value -> println(value) }
//}



///// 流完成-成功完成
//fun simple(): Flow<Int> = (1..3).asFlow()
//fun main() = runBlocking {
//    simple()
//        .onCompletion { cause -> println("Flow completed with $cause") }
//        .collect{ value ->
//            check(value <= 1) { "Collected $value" }
//            println(value)
//        }
//}




//命令式还是声明式
//现在我们知道如何收集流，并以命令式与声明式的方式处理其完成及异常情况。
// 这里有一个很自然的问题是，哪种方式应该是首选的？为什么？
// 作为一个库，我们不主张采用任何特定的方式，并且相信这两种选择都是有效的，
// 应该根据自己的喜好与代码风格进行选择。




///// 启动流
//// 模仿事件流
//fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }
//fun main() = runBlocking {
//    events()
//        .onEach { event -> println("Event: $event") }
//        .collect() // <-- 等待流收集
//    println("Done")
//    println("-----------我的分隔线-----------")
//    events()
//        .onEach { event -> println("Event: $event") }
//        .launchIn(this) // <-- 在单独的协程中执行流
//    println("Done")
//}



///// 流取消检测
//fun foo(): Flow<Int> = flow {
//    for (i in 1..5) {
//        println("Emitting $i")
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//    foo().collect{ value ->
//        if (value == 3) cancel()
//        println(value)
//    }
//
//    (1..5).asFlow().collect{ value ->
//        if (value == 3) cancel()
//        println(value)
//    }
//}



///// 流取消检测-让繁忙的流可取消
//fun main() = runBlocking {
//    (1..5).asFlow().cancellable().collect{ value ->
//        if (value == 3) cancel()
//        println(value)
//    }
//}