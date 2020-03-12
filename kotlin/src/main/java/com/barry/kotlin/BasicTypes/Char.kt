package com.barry.kotlin.BasicTypes

fun main() {
    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9') {
            throw IllegalArgumentException("Out of range")
        }
        return c.toInt() - '0'.toInt()
    }

    println(decimalDigitValue('1'))
}