package com.barry.kotlin.ClassObject

import kotlin.Array

fun main() {

}

class Box<T>(t: T) {
    var value = t
}

val box: Box<Int> = Box<Int>(1)
var box1 = Box(1)

/// 声明处型变
interface Source<out T> {
    fun next(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs
}

/// in。它使得一个类型参数逆变：只可以被消费而不可以被生产
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0拥有类型Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable<Double> 的变量
    val y: Comparable<Double> = x // OK!
}

/// 类型投影
fun copy(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices) {
        to[i] = from[i]
    }
}
val ints: Array<Int> = arrayOf(1,2,3)
val any = Array<Any>(3){""}
//copy(ints, any)