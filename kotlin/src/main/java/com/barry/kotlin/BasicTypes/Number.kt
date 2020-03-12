package com.barry.kotlin.BasicTypes

/**
 * 数字
 */

fun main() {

    /// 对于整数，有四种不同大小的类型。
    val one = 1 // Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long
    val oneByte: Byte = 1

    /// 对于浮点型，提供了 Float 和 Double 类型。
    val pi = 3.14 // Double
    val e = 2.7182818284 // Double
    val eFloat = 2.7182818284F // Float，实际值为2.7182817

    /// kotlin不存在隐式转换
    fun printDouble(d: Double) {
        print(d)
    }

    val i = 1
    val d = 1.1
    val f = 1.1f
    printDouble(d)
//    printDouble(i) // 错误：类型不匹配
//    printDouble(f) // 错误：类型不匹配
}