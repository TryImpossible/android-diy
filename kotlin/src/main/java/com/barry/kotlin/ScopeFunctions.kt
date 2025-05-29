package com.barry.kotlin

import kotlin.random.Random

data class Person(var name: String, var age: Int = 0, var city: String = "") {
    fun moveTo(newCity: String) { city = newCity }
    fun incrementAge() { age++ }
}

fun writeToLog(message: String) {
    println("INFO: $message")
}

class MultiportService(var url: String, var port: Int) {
    fun prepareRequest(): String = "Default request"
    fun query(request: String): String = "Result for query '$request'"
}

/**
 * 五种作用域函数：let、 run、 with、 apply、 以及 also
 */
fun main() {
    Person("Alice", 20, "Amsterdam").let {
        println(it)
        it.moveTo("London")
        it.incrementAge()
        println(it)
    }

    val adam = Person("Adam").apply {
        age = 20
        city = "London"
    }
    println(adam)

    fun getRandomInt(): Int {
        return Random.nextInt(100).also {
            writeToLog("getRandomInt() generated value $it")
        }
    }
    val i = getRandomInt()
    println(i)

    val numberList = mutableListOf<Double>()
    numberList.also { println("populating the list") }
        .apply {
            add(2.71)
            add(3.14)
            add(1.0)
        }
        .also { println("Sorting the list") }
        .sort()
    println(numberList)



    /// let
    val numbers = mutableListOf("one", "two", "three", "four", "five")
    numbers.map { it.length }.filter { it > 3 }.let {
        println(it)
    }
//    numbers.map { it.length }.filter { it > 3 }.let(::println)
    val modifiedFirstItem = numbers.first().let { firstItem ->
        println("The first item of the list if '$firstItem'")
        if (firstItem.length >= 5) firstItem else "!" + firstItem + "!"
    }.uppercase()
    println("First item after modifications: '$modifiedFirstItem'")



    /// with
    with(numbers) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
    }
    val firstAndLast = with(numbers) {
        "The first element is ${first()}," +
        " the last element is ${last()}"
    }
    println(firstAndLast)



    /// run
    val service = MultiportService("https://example.koltinlang.org", 80)
    val result = service.run {
        port = 8080
        query(prepareRequest() + " to port $port")
    }
    // 同样的代码如果用 let() 函数来写
    val letResult = service.let {
        it.port = 8080
        it.query(it.prepareRequest() + " to port ${it.port}")
    }
    println(result)
    println(letResult)



    /// apply
    val adam1 = Person("Adam1").apply {
        age = 32
        city = "London"
    }
    println(adam1)



    /// also
    val numbers1 = mutableListOf("one", "two", "there")
    numbers1.also { println("The list elements before adding new one: $it") }
            .add("four")



    /// takeIf 与 takeUnless
    val number = Random.nextInt(100)
    val evenOrNull = number.takeIf { it % 2 == 0 }
    val oddOrNull = number.takeUnless { it % 2 == 0 }
    println("even: $evenOrNull, odd: $oddOrNull")

    fun displaySubstringPosition(input: String, sub: String) {
        input.indexOf(sub).takeIf { it >= 0 }?.let {
            println("The substring $sub is found in $input.")
            println("Its start position is $it")
        }
    }
    displaySubstringPosition("010000011", "11")
    displaySubstringPosition("010000011", "12")

}
