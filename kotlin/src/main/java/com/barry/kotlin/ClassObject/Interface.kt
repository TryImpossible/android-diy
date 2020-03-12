package com.barry.kotlin.ClassObject

interface MyInterface {
    val prop: Int
    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        println(prop)
    }
}

class Child : MyInterface {
    override val prop: Int
        get() = 29
}

interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String
    override val name: String
        get() = "$firstName $lastName"
}

data class Employee(override val firstName: String, override val lastName: String) : Person

interface A {
    fun foo() {}
    fun bar()
}

interface B {
    fun foo() {
        println("B")
    }

    fun bar() {
        println("bar")
    }
}

class C : A {
    override fun bar() {
        println("bar")
    }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}