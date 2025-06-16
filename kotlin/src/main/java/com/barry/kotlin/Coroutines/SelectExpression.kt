package com.barry.kotlin.Coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlin.random.Random

///// 在通道中select
//fun CoroutineScope.fizz() = produce<String> {
//    while (true) { // 每500毫秒发送一个"Fizz"
//        delay(500)
//        send("Fizz")
//    }
//}
//
//fun CoroutineScope.buzz() = produce<String> {
//    while (true) { // 每1000毫秒发送一个"Buzz"
//        delay(1000)
//        send("Buzz")
//    }
//}
//suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
//    select<Unit> { // <Unit>意味着该select表达式不返回任何结果
//        fizz.onReceive { value -> // 这是第一个select子句
//            println("fizz -> '$value'")
//        }
//        buzz.onReceive { value -> // 这是第一个select子句
//            println("buzz -> '$value'")
//        }
//    }
//}
//fun main() = runBlocking<Unit> {
//    val fizz = fizz()
//    val buzz = buzz()
//    repeat(7) {
//        selectFizzBuzz(fizz, buzz)
//    }
//    coroutineContext.cancelChildren() // 取消fizz和buzz协程
//}



///// 通道关闭时select
//suspend fun selectAorB(a: ReceiveChannel<String>, b: ReceiveChannel<String>): String {
//    return select<String> {
//        a.onReceiveCatching { it ->
//            val value = it.getOrNull()
//            if (value != null) {
//                "a -> '$value'"
//            } else {
//                "Channel 'a' is closed"
//            }
//        }
//        b.onReceiveCatching { it ->
//            val value = it.getOrNull()
//            if (value != null) {
//                "b -> '$value'"
//            } else {
//                "Channel 'b' is closed"
//            }
//        }
//    }
//}
//fun main() = runBlocking<Unit> {
//    val a = produce<String> {
//        repeat(4) {
//            send("Hello $it")
//        }
//    }
//    val b = produce<String> {
//        repeat(4) {
//            send("World $it")
//        }
//    }
//    repeat(8) { // 打印最早的八个结果
//        println(selectAorB(a, b))
//    }
//    coroutineContext.cancelChildren()
//}



///// Select以发送
//fun CoroutineScope.produceNumbers(side: SendChannel<Int>) = produce<Int> {
//    for (num in 1..10) { // 生产1到10的10个数值
//        delay(100) //延迟100毫秒
//        select<Unit> {
//            onSend(num) {} // 发送到主通道
//            side.onSend(num) {} // 或者发送到side通道
//        }
//    }
//}
//fun main() = runBlocking<Unit> {
//    val side = Channel<Int>() // 分配side通道
//    launch { // 对于side通道来说，这是一个很快的消费者
//        side.consumeEach { println("Side channel has $it") }
//    }
//    produceNumbers(side).consumeEach {
//        println("Consuming $it")
//        delay(250) // 不要着急，让我们正确消化消耗被发送来的数字
//    }
//    println("Done consuming")
//    coroutineContext.cancelChildren()
//}



///// Select延迟值
//fun CoroutineScope.asyncString(time: Int) = async {
//    delay(time.toLong())
//    "Waited for $time ms"
//}
//fun CoroutineScope.asyncStringList(): List<Deferred<String>> {
//    val random = Random(3)
//    return List(12) { asyncString(random.nextInt(1000)) }
//}
//fun main() = runBlocking {
//    val list = asyncStringList()
//    val result = select<String> {
//        list.withIndex().forEach { (index, deferred) ->
//            deferred.onAwait{ answer ->
//                "Deferred $index produced answer '$answer'"
//            }
//        }
//    }
//    println(result)
//    val countActive = list.count { it.isActive }
//    println("$countActive coroutines are still active")
//}



/// 在延迟值通道上切换
fun CoroutineScope.switchMapDeferreds(input: ReceiveChannel<Deferred<String>>) = produce<String> {
    var current = input.receive() //从第一个接收到的延迟值开始
    while (isActive) { // 循环直到被取消或关闭
        val next = select<Deferred<String>?> { // 从这个select中返回下一个延迟值或null
            input.onReceiveCatching { update ->
                update.getOrNull()
            }
            current.onAwait { value ->
                send(value) // 发送当前延迟生成的值
                input.receiveCatching().getOrNull() // 然后使用从输入通道得到的下一个延迟值
            }
        }
        if (next == null) {
            println("Channel was closed")
            break
        } else {
            current = next
        }
    }
}
fun CoroutineScope.asyncString(str: String, time: Long) = async {
    delay(time)
    str
}
fun main() = runBlocking<Unit> {
    val chan = Channel<Deferred<String>>() // 测试使用的通道
    launch { // 启动打印协程
        for (s in switchMapDeferreds(chan)) {
            println(s) // 打印每个获得的字符串
        }
    }
    chan.send(asyncString("BEGIN", 100))
    delay(200) // 充足的时间来生产"BEGIN"
    chan.send(asyncString("Slow", 500))
    delay(100) // 不充足的时间来生产"Slow"
    chan.send(asyncString("Replace", 100))
    delay(500) // 在最后一个前给它一点时间
    chan.send(asyncString("END", 500))
    delay(1000) // 给执行一段时间
    chan.close() // 关闭通道...
    delay(500) // 然后等待一段时间来让它结束
}