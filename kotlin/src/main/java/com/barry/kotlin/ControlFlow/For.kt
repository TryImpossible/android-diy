package com.barry.kotlin.ControlFlow

fun main() {
    for (i in 1..3) {
        println(i)
    }
    for (i in 6 downTo 0 step 2) {
        println(i)
    }

    val array = IntArray(5) { it * it }
    for (i in array.indices) {
        print(array[i])
    }
    println()

    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }
}