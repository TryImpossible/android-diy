package com.barry.bytecode

import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildSrcPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("build src plugins")
    }
}