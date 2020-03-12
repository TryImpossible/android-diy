package com.barry.kotlin.ClassObject

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    // 这个 this 关键字在扩展函数内部对应到接收者对象
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun main() {
    /// 扩展是静态解析的
    open class Shape

    class Rectangle : Shape()

    fun Shape.getName() = "Shape"

    fun Rectangle.getName() = "Rectangle"

    fun printClassName(s: Shape) {
        println(s.getName())
    }

    printClassName(Rectangle())

    /// 如果一个类定义有一个成员函数与扩展函数，而这个两函数又有相同类型的接收者类型、相同的名字，并且都适用给定的参数，这种情况总是取成员函数
    class Example {
        fun printFunctionType() {
            println("Class Method")
        }
    }

    fun Example.printFunctionType() {
        println("Extension function")
    }

    Example().printFunctionType();


    /// 可空接收者
    fun Any?.toString(): String {
        if (this == null) return "null"
        // 空检测之后，”this“会自动转换为非空类型，所以下面的 toString()
        // 解析为 Any 类的成员函数
        return toString()
    }

    open class Base {}
    class Derived: Base() {}
    open class BaseCaller {
        open fun Base.printFunctionInfo() {
            println("Base extension funtion in BaseCaller")
        }
        open fun Derived.printFunctionInfo() {
            println("Derived extension function in BaseCaller")
        }
        fun call(b: Base) {
            b.printFunctionInfo()
        }
    }
    class DerivedCaller: BaseCaller() {
        override fun Base.printFunctionInfo() {
            println("Base extension function in DerivedCaller")
        }

        override fun Derived.printFunctionInfo() {
            println("Derived extension function in DerivedCaller")
        }
    }
    BaseCaller().call(Base()) // “Base extension function in BaseCaller”
    DerivedCaller().call(Base()) // “Base extension function in DerivedCaller”——分发接收者虚拟解析
    DerivedCaller().call(Derived()) // “Base extension function in DerivedCaller”——扩展接收者静态解析
}

/// 扩展属性
/// 扩展属性不能有初始化器
val <T> List<T>.lastIndex: Int
    get() = size - 1

/// 伴生对象的扩展
class MyClass {
    companion object {}
}

fun MyClass.Companion.printCompanion() {
    println("companion")
}

