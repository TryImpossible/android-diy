plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.barry.performance"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.barry.performance"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lint {
        // 设置为 true 后，release 构建都会以 Fatal 的设置来运行 Lint。
        // 如果构建时发现了致命（Fatal）的问题，会中止构建（具体由 abortOnError 控制）
        checkReleaseBuilds = true
        // 设置为 true，则当 Lint 发现错误时停止 Gradle 构建
        abortOnError = false

        // 重新指定 Lint 规则配置文件
        lintConfig = file("default-lint.xml")

        // 仅检查指定的问题（根据规则的 id 指定）
        checkOnly += "NewApi" + "InlinedApi"
        // 设置为 true 则检查所有的问题，包括默认不检查问题
        checkAllWarnings = true
        // 不检查指定的问题（根据规则的 id 指定）
        disable += "TypographyFractions" + "TypographyQuotes"
        // 检查指定的问题（根据规则的 id 指定）
        enable += "RtlHardcoded" + "RtlCompat" + "RtlEnabled"

        // 在报告中是否返回对应的 Lint 说明
        explainIssues = true
        // 设置为 true 则错误报告中不包括源代码的行号
        noLines = true
        // 设置为 true 时 Lint 将不报告分析的进度
        quiet = true
        // 设置为 true 则显示一个问题所在的所有地方，而不会截短列表
        showAll = true
        // 设置为 true 则只报告错误
        ignoreWarnings = true
        // 设置为 true，则当有错误时会显示文件的全路径或绝对路径 (默认情况下为true)
        absolutePaths = true

        // 写入报告的路径，默认为构建目录下的 lint-results.html
        htmlOutput = file("lint-report.html")
        // 设置为 true 则会生成一个 HTML 格式的报告
        htmlReport = true
        // 写入检查报告的文件（不指定默认为 lint-results.xml）
        xmlOutput = file("lint-report.xml")
        // 设置为 true 则会生成一个 XML 报告
        xmlReport = false
        // 配置写入输出结果的位置，格式可以是文件或 stdout
        textOutput = file("stdout")
        // 设置为 true，则生成纯文本报告（默认为 false）
        textReport = false

        // 设置为 true，则会把所有警告视为错误处理
        warningsAsErrors = true
//        // 覆盖 Lint 规则的严重程度，例如：
//        severityOverrides ["MissingTranslation": LintOptions.SEVERITY_WARNING]
        // 将指定问题（根据 id 指定）的严重级别（severity）设置为 Fatal
        fatal += "NewApi" + "InlineApi"
        // 将指定问题（根据 id 指定）的严重级别（severity）设置为 Error
        error += "Wakelock" + "TextViewEdits"
        // 将指定问题（根据 id 指定）的严重级别（severity）设置为 Warning
        warning += "ResourceAsColor"
        // 将指定问题（根据 id 指定）的严重级别（severity）设置为 ignore
        ignore += "TypographyQuotes"
    }

    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /// 使用leakcanary
    // debugImplementation because LeakCanary should only run in debug builds.
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14")
    /// 使用ViewServer，连接 HierarchyViewer
    implementation("com.github.romainguy:ViewServer:269059b4e7377b8861e20444502f6adada0d0943")

//    /// 使用blockcanary
//    // most often used way, enable notification to notify block event
//    implementation 'com.github.markzhai:blockcanary-android:1.5.0'

    // this way you only enable BlockCanary in debug package
    // debugImplementation 'com.github.markzhai:blockcanary-android:1.5.0'
    // releaseImplementation 'com.github.markzhai:blockcanary-no-op:1.5.0'

//    debugImplementation('com.github.bzcoder:blockcanarycompat-android:0.0.4')
//    releaseImplementation('com.github.bzcoder:blockcanarycompat-android-no-op:0.0.4')

    //引入卡顿监控实现依赖库
    implementation("io.github.knight-zxw:blockcanary:0.0.5")
    //引入卡顿消息通知及相关展示UI
    implementation("io.github.knight-zxw:blockcanary-ui:0.0.5")

    // 如果你只想在debug包引入，不希望被引入release包，可以使用 debugImplementation
    //debugImplementation 'io.github.knight-zxw:blockcanary:0.0.5'
}