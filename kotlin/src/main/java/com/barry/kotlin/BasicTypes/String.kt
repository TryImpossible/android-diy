package com.barry.kotlin.BasicTypes

/**
 * 字符串用 String 类型表示。
 * 字符串是不可变的。
 * 字符串的元素，字符可以用索引运算符访问 s[i]
 */
fun main() {
    val str: String = "Barry"
    for (c in str) {
        println(c)
    }

    val s = "abc" + 1
    println(s + "def")

    val s1 = "Hello, world!\n"
    val text = """
        for (c in "foo")
            print(c)
    """.trimIndent()
    println("text is ${text}")

    val i = 10
    println("i = $i")

    val s3 = "abc"
    println("s3.length is ${s3.length}")

    val price = """
        ${'$'}9.99
    """.trimIndent()
    println("price is ${price}")
}