pluginManagement {
    repositories {
        maven(url = "$rootDir/bytecode/repos/releases")
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven (url = "https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (url = "https://jitpack.io")
    }
}

rootProject.name = "android-diy"
include(":activity")
include(":performance")
//include(":baselib")
include(":multichannel")
include(":animation")
include(":motionlayout")
include(":customview")
include(":agentweb")
//include(":uniapplets")
//include(":app")
include(":java")
include(":kotlin")
include(":coordinatorlayout")
include(":draw9patch")
include(":eventdispatch")
include(":recyclerview")
include(":sqlite")
include(":contentprovider")
include(":designpattern")
include(":algorithm")
include(":bytecode_app")
include(":bytecode")
