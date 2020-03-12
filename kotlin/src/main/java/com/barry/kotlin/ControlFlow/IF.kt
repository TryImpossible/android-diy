package com.barry.kotlin.ControlFlow

fun main() {
    // 传统用法
    val a = 1
    val b = 2

    var max = a
    if (a < b) max = b

    // With else
    if (a > b) {
        max = a
    } else {
        max = b
    }

    // 作为表达式
    max = if (a > b) a else b

    // if 的分支可以是代码块，最后的表达式作为该块的值
    max = if (a > b) {
        println("Choose a")
        a
    } else {
        println("Choose b")
        b
    }
}