package com.barry.kotlin.ClassObject

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
    Derived(b).printMessage()
    Derived(b).printMessageLine()
}

interface Base2 {
    val message: String
    fun print()
    fun printMessage()
    fun printMessageLine()
}
class BaseImpl(val x: Int): Base2 {
    override val message: String
        get() = "BaseImpl: x = $x"

    override fun print() {
        println(message)
    }

    override fun printMessage() {
        println(x)
    }

    override fun printMessageLine() {
        println(x)
    }
}
class Derived(b: Base2): Base2 by b {
    // 在 b 的 `print` 实现中不会访问到这个属性
    override val message = "Message of Derived"
}