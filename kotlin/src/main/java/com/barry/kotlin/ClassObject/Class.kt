package com.barry.kotlin.ClassObject

import android.content.Context
import android.util.AttributeSet
import android.view.View

fun main() {
    val orderDemo = InitOrderDemo("Barry")
    Derived1("Barry", "Tse")
}

/// 类声明由类名、类头（指定其类型参数、构造函数等）以及由花括号包围的类体构成的。类头与类体都是可选的
class Invoice {}

class Empty

/// 在kotlin中的一个类可以有一个主构造函数以及一个或多个次构造函数。主构造函数是类头的一部分：它跟在类名（与可选的类型参数）后
class Person1 constructor(firstName: String) {}

class Person2(firstName: String) {}

/// 主构造函数中不能包含任何的代码。初始化的代码可以说放到以 init 关键字作为前缀的初始化块中
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also { println(it) }

    init {
        println("First Initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

/// 主构造函数的参数可以在初始化块中使用，它们也可以在类体内声明的属性初始化器中使用
class Customer(name: String) {
    val customerKey = name.toUpperCase()
}

/// 声明属性以及从主构造函数初始化属性
class Person3(val firstName: String, val lastName: String, var age: Int) {}

/// 类也可以声明前缀有 constructor 的次构造函数
class Person4 {
    var children: MutableList<Person4> = mutableListOf();

    constructor(parent: Person4) {
        parent.children.add(this)
    }
}

/// 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，可以直接委托或者通过次构造函数间接委托。
/// 委托到同一个类的另一个构造函数用 this 关键字即可
class Person5(val name: String) {
    var children: MutableList<Person5> = mutableListOf();

    constructor(name: String, parent: Person5) : this(name) {
        parent.children.add(this)
    }
}

class Constructors {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

open class Base(p: Int)
class Divider(p: Int) : Base(p)

class MyView : View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}

/// 覆盖属性和方法
open class Shape {
    open val vertexCount: Int = 0
    open fun draw() {}
    fun fill() {}
}

class Circle() : Shape() {
    override fun draw() {
        super.draw()
    }
}

class Rectangle : Shape() {
    override val vertexCount = 4
}

interface Shape1 {
    val vertexCount: Int
}

class Rectangle1(override val vertexCount: Int = 4) : Shape1;
class Polygon1 : Shape1 {
    override var vertexCount: Int = 0
}


/// 派生类初始化顺序
open class Base1(val name: String) {
    init {
        println("Initializing Base")
    }

    open val size: Int = name.length.also { println("Initializing size in Base: $it") }
}

class Derived1(name: String, val lastName: String) : Base1(name.capitalize().also { println("Argument for Base: $it") }) {
    init {
        println("Initializing Derived")
    }

    override val size: Int = (super.size + lastName.length).also { println("Initializing size in Dervied: $it") }
}

open class Rectangle2 {
    open fun draw() {
        println("Drawing a rectangle")
    }

    val borderColor: String get() = "black"
}

class FilledRectangle : Rectangle2() {
    override fun draw() {
        super.draw()
        println("Filling the rectangle")
    }

    val fillColor: String get() = "black"

    inner class Filler {
        fun fill() {}
        fun drawAndFill() {
            super@FilledRectangle.draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}")
        }
    }
}

open class Rectangle3 {
    open fun draw() {}
}

interface Polygon3 {
    fun draw() {} // 接口成员默认就是 open
}

class Square() : Rectangle3(), Polygon3 {
    override fun draw() {
        super<Rectangle3>.draw()
        super<Polygon3>.draw()
    }
}

open class Polygon {
    open fun draw() {}
}

abstract class Retangle : Polygon() {
    override abstract fun draw()
}