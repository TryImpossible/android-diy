package com.barry.bytecode

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("我是Standardalone project方式的自定义插件")
    }
}