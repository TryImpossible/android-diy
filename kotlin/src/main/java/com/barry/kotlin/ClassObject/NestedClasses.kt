package com.barry.kotlin.ClassObject

fun main() {

}

/// 嵌套类
class Outer1 {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}
val demo = Outer1.Nested().foo() // == 2

/// 内部类
class Outer2 {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}
val demo2 = Outer2().Inner().foo() // == 1

// 匿名内部类
//window.addMouseListener(object: MouseAdaper) {
//    override fun mouseClicked(e: MouseEvent) {}
//    override fun mouseEntered(e: MouseEvent) {}
//}