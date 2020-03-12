package com.barry.kotlin.BasicTypes

/**
 * 使用库函数 arrayOf() 创建指定大小的数组
 * 使用库函数 arrayOfNulls() 创建指定大小元素都为空的数组
 */
fun main() {
    val asc = Array(5) { i -> (i * i).toString() }
    asc.forEach { print(it) }
    println()

    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]

    // 大小为5，值为 [0, 0, 0, 0, 0]的整形数组
    var arr = IntArray(5)

    // 大小为 5、值为 [42, 42, 42, 42, 42] 的整型数组
    var arr1 = IntArray(5) { 42 }

    // 大小为 5、值为 [0, 1, 2, 3, 4] 的整型数组（值初始化为其索引值）
    var arr2 = IntArray(5) { it * 1 }
}