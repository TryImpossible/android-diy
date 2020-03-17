package com.barry.kotlin.ClassObject

fun main() {
    open class A(x: Int) {
        public open val y: Int = x;
    }

    val ab: A = object : A(1), B {
        override val y = 15
    }

    fun foo() {
        val adHoc = object {
            var x: Int = 0
            var y: Int = 0
        }
        println(adHoc.x + adHoc.y)
    }
}

class C1 {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        var x1 = foo().x
//        var x2 = publicFoo().x
    }
}