package com.barry.kotlin.ControlFlow

fun foo1() {
    listOf<Int>(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // 非局部直接返回到 foo() 的调用者
        print(it)
    }
    println("this point is unreachable")
}

fun foo2() {
    listOf<Int>(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    println("done with explicit label")
}

fun foo3() {
    listOf<Int>(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    println("done with explicit label")
}

fun foo4() {
    listOf<Int>(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return // 局部函数返回到匿名函数的调用者，即 forEach 循环
        print(value)
    })
    println("done with anonymous function")
}

fun foo5() {
    run loop@{
        listOf<Int>(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // 从传入 run 的 lambda 表达式非局部返回
            print(it)
        }
    }
    println(" done with nested loop")
}

fun main() {
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            println("j is $j")
            if (j == 5) break@loop
        }
    }

    foo4()
    foo5()
}

