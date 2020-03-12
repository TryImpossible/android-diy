package com.barry.kotlin.BasicTypes

fun main() {
    /// 整数间的除法总是返回整数，会丢弃任何小数部分。
    val x = 5 / 2
//    println(x == 2.5) // cannot be applied to 'Int' and 'Double'
    println(x == 2)

    val y = 5L / 2
    println(y == 2L)

    val z = 5 / 2.toDouble()
    println(z == 2.5)

    /// 完整的位运算列表（只用于 Int 与 Long）
    /// shl 有符号左移
    /// shr 有符号右移
    /// ushr 无符号右移
    /// and 位与
    /// or 位或
    /// xor 位异或
    /// inv 位非

    /// 浮点数比较
    /// 相等性比较： a == b 与 a != b
    /// 比较操作符：a < b 、a > b 、a <= b 、a >= b
    /// 区间实例以及区间检测：a..b 、 x in a..b 、 x !in a..b

}