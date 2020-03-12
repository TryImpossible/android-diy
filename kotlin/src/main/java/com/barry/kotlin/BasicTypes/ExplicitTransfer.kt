package com.barry.kotlin.BasicTypes

/**
 * 显示转换
 */
fun main() {
    val a: Int = 1000
    println(a === a) // 相等
    val boxedA: Int = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA) // false
    println(boxedA == anotherBoxedA) // true

    val b: Byte = 1 // Ok，字面值是静态检测的
//    val i: Int = b // 错误
    val i: Int = b.toInt() // Ok，显示转换
    println(i)


    /// 每个数字类型支持如下的转换：
    /// toByte(): Byte
    /// toShort(): Short
    /// toInt(): Int
    /// toLong(): Long
    /// toFloat(): Float
    /// toDouble(): Double
    /// toChar(): Char
}