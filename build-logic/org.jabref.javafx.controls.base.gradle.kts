plugins {
    id("java")
    id("org.gradlex.java-module-dependencies")
    id("org.gradlex.java-module-testing")
    id("org.gradlex.jvm-dependency-conflict-resolution")
}

group = "org.jabref.javafx.controls"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(24))
testing.suites.register<JvmTestSuite>("testFunctional")
tasks.check { dependsOn(tasks.named("testFunctional")) }

jvmDependencyConflicts {
    consistentResolution {
        providesVersions(":app")
    }
}
dependencies {

}

tasks.withType<Test>().configureEach {
    jvmArgs("-Dorg.slf4j.simpleLogger.defaultLogLevel=error")
}
