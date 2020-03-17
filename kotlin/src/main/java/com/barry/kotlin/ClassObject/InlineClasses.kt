package com.barry.kotlin.ClassObject

fun main() {
    val securePassword = Password("Don't try this in production")

    val name = Name("kotlin")
    name.greet() // `greet` 方法会作为一个静态方法调用
    println(name.length) // 属性的 get 方法会作为一个静态方法被调用

    val name1 = Name1("kotlin")
    println(name1.prettyPrint())
}

// 内联类必须含有唯一的一个属性在主构造函数中初始化
inline class Password(val value: String)

/// 成员
inline class Name(val s: String) {
    val length: Int
        get() = s.length
    fun greet() {
        println("Hello, $s")
    }
}

// 继承
interface Printable{
    fun prettyPrint(): String
}
inline class Name1(val s: String): Printable {
    override fun prettyPrint(): String = "Let's $s"
}

