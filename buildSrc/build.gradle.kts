plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains:annotations:15.0")
    // 必须添加：Gradle和Android Gradle Plugin的API，这样我们才能调用Transform等类
    compileOnly("com.android.tools.build:gradle:8.6.0") // 版本号请与你项目根目录的build.gradle中使用的AGP版本保持一致！
    // 可选：用于操作字节码的库，如ASM、Javassist等。这里以ASM为例，它是功能最强大、最常用的字节码操作框架。
    implementation("org.ow2.asm:asm:9.8")
    implementation("org.ow2.asm:asm-commons:9.8")
    implementation("org.ow2.asm:asm-util:9.8")
}

// 如果你想把插件发布出去，可以配置这个block
gradlePlugin {
    plugins {
        create("BuildSrcPlugin") { // 插件内部标识符
            id = "com.barry.bytecode.buildSrc" // 插件发布后的ID，用户apply plugin时使用
            implementationClass = "com.barry.bytecode.BuildSrcPlugin" // 插件入口类的全限定名
            version = "1.0.0"
        }
    }
}

// 1. 在需要调试的项目中，引入 id("com.barry.bytecode.buildSrc") 插件
// 2. 在Gradle面板中，找到需要调试的Task，右键Debug即可