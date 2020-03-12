package com.barry.kotlin.ClassObject

/**
 * 我们经常创建一些只保存数据的类。在这些类中，一些标准函数往往是从数据机械推导而来的
 */
fun main() {
    data class User(val name: String, val age: Int) {
    }

    fun copy(name: String = "", age: Int = 0) = User(name, age)

    data class Person(val name: String) {
        var age: Int = 0
    }


    val jack = User(name = "Jack", age = 1)
    val oldJack = jack.copy(age = 2)

    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age")
}
