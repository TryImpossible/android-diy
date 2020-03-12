package com.barry.kotlin.ClassObject

private fun foo() {} // 在VisibilityModifiers.kt可见
public var bar: Int = 5 // 该属性随处可见
    private set         // set只在VisibilityModifiers.kt可见

internal val baz = 6 // 相同模块内可见

open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 2
    val d = 4 // 默认 public

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass: Outer() {
    // a 不可见
    // b、c、d 可见
    // Nested 和 e 可见
    override val b = 5 // "b"为protected
}

class Unrelated(o: Outer) {
    // o.a 和 o.b 不可见
    // o.c 和 o.d 可见（相同模块内）
    // Outer.Nested 不可见，Nested::e 也不可见
}

class CBA private constructor(a: Int) {}
