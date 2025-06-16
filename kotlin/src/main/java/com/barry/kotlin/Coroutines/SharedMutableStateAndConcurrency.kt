package com.barry.kotlin.Coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

///// 问题
//suspend fun massiveRun(action: suspend () -> Unit) {
//    val n = 100 // 启动的协程数量
//    val k = 1000 // 每个协程重复执行同一动作的次数
//    val time = measureTimeMillis {
//        coroutineScope { // 协程的作用域
//            repeat(n) {
//                launch {
//                    repeat(k) {
//                        action()
//                    }
//                }
//            }
//        }
//    }
//    println("Completed ${n * k} actions in $time ms")
//}
//@Volatile // 有一种常见的误解：volatile 可以解决并发问题 在 Kotlin 中 `volatile` 是一个注解
//var counter = 0
//fun main() = runBlocking {
//    withContext(Dispatchers.Default) {
//        massiveRun {
//            counter++
//        }
//    }
//    println("Counter = $counter")
//}



///// 线程安全的数据结构
///// 一种对线程、协程都有效的常规解决方法，就是使用线程安全（也称为同步的、 可线性化、原子）的数据结构，它为需要在共享状态上执行的相应操作提供所有必需的同步处理。
//suspend fun massiveRun(action: suspend () -> Unit) {
//    val n = 100 // 启动的协程数量
//    val k = 1000 // 每个协程重复执行同一动作的次数
//    val time = measureTimeMillis {
//        coroutineScope { // 协程的作用域
//            repeat(n) {
//                launch {
//                    repeat(k) {
//                        action()
//                    }
//                }
//            }
//        }
//    }
//    println("Completed ${n * k} actions in $time ms")
//}
//val counter = AtomicInteger()
//fun main() = runBlocking {
//    withContext(Dispatchers.Default) {
//        massiveRun {
//            counter.incrementAndGet()
//        }
//    }
//    println("Counter = $counter")
//}



///// 以细粒度限制线程
///// 限制线程 是解决共享可变状态问题的一种方案：对特定共享状态的所有访问权都限制在单个线程中。它通常应用于 UI 程序中：所有 UI 状态都局限于单个事件分发线程或应用主线程中
//suspend fun massiveRun(action: suspend () -> Unit) {
//    val n = 100 // 启动的协程数量
//    val k = 1000 // 每个协程重复执行同一动作的次数
//    val time = measureTimeMillis {
//        coroutineScope { // 协程的作用域
//            repeat(n) {
//                launch {
//                    repeat(k) {
//                        action()
//                    }
//                }
//            }
//        }
//    }
//    println("Completed ${n * k} actions in $time ms")
//}
//val counterContext = newSingleThreadContext("CounterContext")
//var counter = 0
//fun main() = runBlocking {
//    withContext(Dispatchers.Default) {
//        massiveRun {
//            // 将每次自增限制在单线程上下文中
//            withContext(counterContext) {
//                counter++
//            }
//        }
//    }
//    println("Counter = $counter")
//}



///// 以粗粒度限制线程
///// 在实践中，线程限制是在大段代码中执行的，例如：状态更新类业务逻辑中大部分都是限于单线程中。下面的示例演示了这种情况， 在单线程上下文中运行每个协程。
//suspend fun massiveRun(action: suspend () -> Unit) {
//    val n = 100 // 启动的协程数量
//    val k = 1000 // 每个协程重复执行同一动作的次数
//    val time = measureTimeMillis {
//        coroutineScope { // 协程的作用域
//            repeat(n) {
//                launch {
//                    repeat(k) {
//                        action()
//                    }
//                }
//            }
//        }
//    }
//    println("Completed ${n * k} actions in $time ms")
//}
//val counterContext = newSingleThreadContext("CounterContext")
//var counter = 0
//fun main() = runBlocking {
//    // 将一切都限制在单线程上下文中
//    withContext(counterContext) {
//        massiveRun {
//            counter++
//        }
//    }
//    println("Counter = $counter")
//}



///// 互斥
///// 该问题的互斥解决方案：使用永远不会同时执行的 关键代码块 来保护共享状态的所有修改。在阻塞的世界中，你通常会为此目的使用 synchronized 或者 ReentrantLock。 在协程中的替代品叫做 Mutex 。它具有 lock 和 unlock 方法， 可以隔离关键的部分。关键的区别在于 Mutex.lock() 是一个挂起函数，它不会阻塞线程。
///// 还有 withLock 扩展函数，可以方便的替代常用的 mutex.lock(); try { …… } finally { mutex.unlock() } 模式：
//suspend fun massiveRun(action: suspend () -> Unit) {
//    val n = 100 // 启动的协程数量
//    val k = 1000 // 每个协程重复执行同一动作的次数
//    val time = measureTimeMillis {
//        coroutineScope { // 协程的作用域
//            repeat(n) {
//                launch {
//                    repeat(k) {
//                        action()
//                    }
//                }
//            }
//        }
//    }
//    println("Completed ${n * k} actions in $time ms")
//}
//val mutex = Mutex()
//var counter = 0
//fun main() = runBlocking {
//    withContext(Dispatchers.Default) {
//        massiveRun {
//            // 用锁保护每次自增
//            mutex.withLock {
//                counter++
//            }
//        }
//    }
//    println("Counter = $counter")
//}