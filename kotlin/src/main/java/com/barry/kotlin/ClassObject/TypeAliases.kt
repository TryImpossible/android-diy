package com.barry.kotlin.ClassObject

import android.net.Network
import java.io.File

fun main() {
    fun foo(p: Predicate<Int>) = p(42)
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f))

    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p))
}

/// 例如，通常缩减集合类型是很有吸引力的
typealias NodeSet = Set<Network>;

typealias FileTable<K> = MutableMap<K, MutableList<File>>

/// 你可以为函数类型提供另外的别名
typealias MyHandler = (Int, String, Any) -> Unit

typealias Predicate<T> = (T) -> Boolean

/// 你可以为内部内和嵌套类创建新名称
class A1 {
    inner class Inner
}

class B2 {
    inner class Inner
}
typealias AInner = A1.Inner
typealias BInner = B2.Inner