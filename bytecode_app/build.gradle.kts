//rootProject.allprojects {
//    repositories {
//        google()
//        mavenCentral()
//        flatDir {
//            dirs(project(":bytecode_app").file("libs"))
//        }
//    }
//}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("greeting-plugin")
    id("com.barry.bytecode.buildSrc")
    id("com.barry.bytecode.standardAlonePlugin")
    id("com.barry.bytecode.fixThirdPartyLibPlugin")
}

apply(from = "other.gradle.kts")

android {
    namespace = "com.barry.bytecode_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.barry.bytecode_app"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(files("libs/third-party-lib-release.aar"))
//    implementation(":third-party-lib-release@aar")

//    // 本地文件
//    implementation(files("libs/third-party-lib-release.jar"))
//    // 模块依赖
//    implementation(project(":bytecode_app"))
//    // 文件树
//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
//    // map格式
//    implementation(mapOf("name" to "third-party-lib-release", "ext" to "aar"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

project.afterEvaluate {
    val buildTask = project.tasks.getByName("build")
    buildTask.doLast {
        println("监听build任务执行自定义逻辑")
    }
}

// 自定义任务
project.tasks.register("MyTask") {
    doLast {
        println("我是build.gradle.kts中的自定义任务")
    }
}

class GreetingPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.task("hello") {
            doLast {
                println("Script plugins : Hello from the GreetingPlugin")
            }
        }
    }
}

// Apply the plugin
apply<GreetingPlugin>()
