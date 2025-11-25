import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform

plugins {
    id("java-platform")
}

javaPlatform {
    allowDependencies()
}

val javafx = 24

dependencies {

}

dependencies.constraints {
    api("org.openjfx:javafx-base:$javafx")
   // api("org.openjfx:javafx-controls:$javafx")
    // api("org.openjfx:javafx-fxml:$javafx")
    api("org.openjfx:javafx-graphics:${javafx}")
    api("org.controlsfx:controlsfx:11.2.2")
   // api("org.openjfx:javafx-swing:$javafx")
  //  api("org.openjfx:javafx-web:$javafx")
    // from JavaFX25 onwards
  //  api("org.openjfx:jdk-jsobject:$javafx")

}
