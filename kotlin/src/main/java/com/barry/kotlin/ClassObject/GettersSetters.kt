package com.barry.kotlin.ClassObject

fun main() {
    /// 1.只读属性的用val开始代替var，2.只读属性不允许setter
    val simple: Int?
    val inferredType = 1

}

class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"

    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            value.trim()
        }
}

fun copyAddress(address: Address): Address {
    val result = Address()
    result.name = address.name
    result.street = address.street;
    return result
}

/// 需要改变一个访问器的可见性或者对其注解，但是不需要改变默认的实现，可以定义访问器面不定义其实现：
//var setterVisibilty: String = "abc"
//    private set
//var setterWithAnnotaion: Any? = null
//    @Inject set

private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if (_table == null) {
            _table = HashMap()
        }
        return _table ?: throw AssertionError("Set to null by another thread")
    }