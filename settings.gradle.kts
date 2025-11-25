pluginManagement {
    includeBuild("build-logic")


    repositories {
        gradlePluginPortal()
    }
}
plugins {
    id("org.jabref.javafx.controls.gradle.build")
}

rootProject.name = "org.jabref.javafx.controls"

javaModules {
    directory(".")
    versions("versions")
    // include("jablib", "jabkit", "jabgui", "jabsrv", "jabsrv-cli", "test-support", "versions")
}

