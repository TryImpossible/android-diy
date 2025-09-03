plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("java-gradle-plugin")
    id("maven-publish")
    signing
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation("org.jetbrains:annotations:26.0.2")
    // 必须添加：Gradle和Android Gradle Plugin的API，这样我们才能调用Transform等类
    implementation("com.android.tools.build:gradle:8.6.0") // 版本号请与你项目根目录的build.gradle中使用的AGP版本保持一致！
    // 可选：用于操作字节码的库，如ASM、Javassist等。这里以ASM为例，它是功能最强大、最常用的字节码操作框架。
    implementation("org.ow2.asm:asm:9.8")
    implementation("org.ow2.asm:asm-commons:9.8")
    implementation("org.ow2.asm:asm-util:9.8")
}

gradlePlugin {
    plugins {
        create("StandardAlonePlugin") {
            id = "com.barry.bytecode.standardAlonePlugin"
            implementationClass = "com.barry.bytecode.StandardAlonePlugin"
        }
    }
    plugins {
        create("FixThirdPartyLibPlugin") {
            id = "com.barry.bytecode.fixThirdPartyLibPlugin"
            implementationClass = "com.barry.bytecode.thirdparty.FixThirdPartyLibPlugin"
        }
    }
}

// 1. 在需要调试的项目中，引入 id("com.barry.bytecode") 插件
// 2. 以debug模式启动配置的"debug bytecode gradle plugin"选项
// 3. 执行gradle命令：./gradlew :bytecode_app:build -Dorg.gradle.debug=true --no-daemon

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("ByteCodePlugin") {
            groupId = "com.barry.bytecode"
            artifactId = "ByteCodePlugin"
            version = "1.0.0"

            from(components["java"])

            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name = "My Plugin"
                description = "A concise description of my library"
                url = "http://www.example.com/library"
                properties = mapOf(
                    "myProp" to "value",
                    "prop.with.dots" to "anotherValue"
                )
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "byte"
                        name = "Barry"
                        email = "865068275@qq.com"
                    }
                }
                scm {
                    connection = "scm:git:git://example.com/my-library.git"
                    developerConnection = "scm:git:ssh://example.com/my-library.git"
                    url = "http://example.com/my-library/"
                }
            }
        }
    }

    repositories {
        maven {
            name = "localRepo"
            // change URLs to point to your repos, e.g. http://my.org/repo
//            val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
//            val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            val releasesRepoUrl = uri("repos/releases")
            val snapshotsRepoUrl = uri("repos/snapshots")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        }
    }
}

//signing {
//    sign(publishing.publications["MyPlugin"])
//}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}