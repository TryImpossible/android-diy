package com.barry.kotlin.ControlFlow

import android.app.DownloadManager
import android.app.VoiceInteractor

fun main() {
    val x = 1;

    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> {
            println("x is neither 1 nor 2")
        }
    }

    when (x) {
        1, 2 -> println("x == 1 or x == 2")
        else -> println("otherwise")
    }

    val s: Int = 123
    val parseInt = fun(x: Int): Int = x.toInt()
    when (x) {
        parseInt(s) -> println("s encodes x")
        else -> println("s does not encodes x")
    }

    val validNumbers = IntArray(5) { it * 1 };
    when (x) {
        in 1..10 -> println("x is in the range")
        in validNumbers -> println("x is valid")
        !in 10..20 -> println("x is outside the range")
        else -> println("none of the above")
    }

    fun hasPrefix(x: Any) = when (x) {
        is String -> x.startsWith("prefix")
        else -> false
    }

    when {
//        x.isOdd() -> println("x is Odd")
//        x.isEven() -> println("x is even")
        else -> println("x is funny")
    }

//    fun Request.getBody() =
//            when (val response = executeRequest()) {
//                is Success -> response.body
//                is HttpError -> throw HttpException(response.status)
//            }
}