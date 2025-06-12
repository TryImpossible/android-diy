package com.barry.kotlin.Coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

///// 通道基础
//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        // 这里可能是消耗大量CPU运算的异步逻辑
//        // 我们将仅仅做5次整数的平方并发送
//        for (x in 1..5) channel.send(x * x)
//    }
//    // 这里我们打印了5次被接收的整数
//    repeat(5) {
//        println(channel.receive())
//    }
//    println("Done")
//}



///// 关闭与迭代通道
//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1..5) channel.send(x * x)
//        channel.close() // 我们结束发送
//    }
//    // 这里我们使用for循环来打印所有被接收到的元素（直到通道被关闭）
//    for (y in channel) println(y)
//    println("Done")
//}



///// 构建通道生产者
//fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce<Int>{
//    for (x in 1..5) send(x * x)
//}
//fun main() = runBlocking {
//    val squares = produceSquares()
//    squares.consumeEach { println(it) }
//    println("Done!")
//}



///// 管道
//fun CoroutineScope.produceNumbers() = produce<Int> {
//    var x = 1
//    while (true) send(x++) // 在流中开始从1生产无穷多个整数
//}
//fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce<Int> {
//    for (x in numbers) send(x * x)
//}
//fun main() = runBlocking {
//    val numbers = produceNumbers() // 从1开始生成整数
//    val squares = square(numbers) // 整数求平方
//    repeat(5) {
//        println(squares.receive()) // 输出前5个
//    }
//    println("Done!") // 至此完成
//    coroutineContext.cancelChildren() // 取消子协程
//}



///// 使用管道的素数
//fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
//    var x = start
//    while (true) send(x++) // 从start开始过滤整数流
//}
//fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
//    for (x in numbers) if (x % prime != 0) send(x)
//}
//fun main() = runBlocking {
//    var cur = numbersFrom(2)
//    repeat(10) {
//        val prime = cur.receive();
//        println(prime)
//        cur = filter(cur, prime)
//    }
//    coroutineContext.cancelChildren() // 取消所有的子协程来让主协程结束
//}



///// 扇出
//fun CoroutineScope.produceNumbers() = produce<Int> {
//    var x = 1 // 从1开始
//    while (true) {
//        send(x++) // 产生下一个数字
//        delay(100) // 等待0.1秒
//    }
//}
//fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
//    for (msg in channel) {
//        println("Processor #$id received $msg")
//    }
//}
//fun main() = runBlocking {
//    val producer = produceNumbers()
//    repeat(5) {
//        launchProcessor(it, producer)
//    }
//    delay(950)
//    producer.cancel() // 取消协程生产者从而将它们全部杀死
//}



///// 扇入
//suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
//    while (true) {
//        delay(time)
//        channel.send(s)
//    }
//}
//fun main() = runBlocking {
//    val channel = Channel<String>()
//    launch { sendString(channel, "foo", 200L) }
//    launch { sendString(channel, "BAR!", 500L) }
//    repeat(6) { // 接收前6个
//        println(channel.receive())
//    }
//    coroutineContext.cancelChildren() // 取消所有子协程来让主协程结束
//}



///// 带缓冲的通道
//fun main() = runBlocking {
//    val channel = Channel<Int>(4) // 启动带缓冲的通道
//    val sender = launch { // 启动发送者协程
//        repeat(10) {
//            println("Sending $it") // 在每一个元素发送前打印它们
//            channel.send(it) // 将在缓冲区被占满时挂起
//        }
//    }
//    // 没有接收到东西...只是等待...
//    delay(100)
//    sender.cancel() // 取消发送者协程
//}




///// 通道是公平的
//data class Ball(var hits: Int)
//suspend fun player(name: String, table: Channel<Ball>) {
//    for (ball in table) { // 在循环中接收球
//        ball.hits++
//        println("$name $ball")
//        delay(300) // 等待一段时间
//        table.send(ball) // 将球发送回去
//    }
//}
//fun main() = runBlocking {
//    val table = Channel<Ball>() // 一个共享的table桌子
//    launch { player("ping", table) }
//    launch { player("pong", table) }
//    table.send(Ball(0)) // 乒乓球
//    delay(1000) // 延迟1秒钟
//    coroutineContext.cancelChildren() // 游戏结束，取消它们
//}



///// 计时器通道
//fun main() = runBlocking {
//    val tickerChannel = ticker(delayMillis = 200, initialDelayMillis = 0) // 创建计时器通道
//    var nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
//    println("Initial element is available immediately: $nextElement") // no initial delay
//
//    nextElement = withTimeoutOrNull(100) { tickerChannel.receive() } // all subsequent element have 200ms delay
//    println("Next element is not ready in 100ms: $nextElement") // no initial delay
//
//    nextElement = withTimeoutOrNull(120) { tickerChannel.receive() } // all subsequent element have 200ms delay
//    println("Next element is ready in 200ms: $nextElement") // no initial delay
//
//    // 模拟大量消费延迟
//    println("Consumer pauses for 300ms")
//    delay(300)
//    // 下一个元素立即可用
//    nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
//    println("Next element is available immediately after large consumer delay: $nextElement")
//    // 请注意，`receive`调用之间的暂停被考虑在内，下一个元素的到达速度更快
//    nextElement = withTimeoutOrNull(120) { tickerChannel.receive() }
//    println("Next element is ready in 100ms after consumer pause in 300ms: $nextElement")
//
//    tickerChannel.cancel() // 表明不再需要更多的元素
//}