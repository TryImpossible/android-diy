package com.barry.kotlin.ClassObject

fun main() {
}

/// 枚举类
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

/// 初始化
/// 因为每一个枚举都是枚举类的实例，所以他们可以是这样初始化的
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

/// 匿名类
enum class ProtocolState {
    WARING {
        override fun signal() = TALKING
    },
    TALKING {
        override fun signal() = WARING
    };

    abstract fun signal(): ProtocolState
}

/// 在枚举中实现接口
//enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
////    PULS {
////        override fun apply(t: Int, u: Int) = t + u
////    },
////    TIMS {
////        override fun apply(t: Int, u: Int) = t - u
////    };
////
////    override fun applyAsInt(left: Int, right: Int) = apply(t, u)
////}


/// 使用枚举常量
enum class RGB { RED, GREEN, BLUE }
inline fun <reified T: Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}
//printAllValues<RGB>() // 输出 RED, GREEN, BLUE

/// 每个枚举常量都具有在枚举类声明中获取其名称与位置的属性：
//val name: String
//val ordinal: Int