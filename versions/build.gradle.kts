import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform

plugins {
    id("java-platform")
}

javaPlatform {
    allowDependencies()
}

// Based on https://stackoverflow.com/questions/11235614/how-to-detect-the-current-os-from-gradle
val os =
    DefaultNativePlatform.getCurrentOperatingSystem()
val arch =
    DefaultNativePlatform.getCurrentArchitecture()
val javafx = 24


dependencies.constraints {
    api("org.openjfx:javafx-base:$javafx")
    api("org.openjfx:javafx-controls:$javafx")
    api("org.openjfx:javafx-fxml:$javafx")
    api("org.openjfx:javafx-graphics:${javafx}")
    api("org.openjfx:javafx-swing:$javafx")
    api("org.openjfx:javafx-web:$javafx")
    // from JavaFX25 onwards
  //  api("org.openjfx:jdk-jsobject:$javafx")

}
