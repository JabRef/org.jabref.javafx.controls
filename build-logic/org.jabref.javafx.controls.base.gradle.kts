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
        platform(":versions")
    }
}

jvmDependencyConflicts.patch {
    listOf("base", "graphics", "controls").forEach { jfxModule ->
        module("org.openjfx:javafx-$jfxModule") {
            addTargetPlatformVariant("linux", OperatingSystemFamily.LINUX, MachineArchitecture.X86_64)
            addTargetPlatformVariant("linux-aarch64", OperatingSystemFamily.LINUX, MachineArchitecture.ARM64)
            addTargetPlatformVariant("mac", OperatingSystemFamily.MACOS, MachineArchitecture.X86_64)
            addTargetPlatformVariant("mac-aarch64", OperatingSystemFamily.MACOS, MachineArchitecture.ARM64)
            addTargetPlatformVariant("win", OperatingSystemFamily.WINDOWS, MachineArchitecture.X86_64)
        }
    }
}


tasks.withType<Test>().configureEach {
    jvmArgs("-Dorg.slf4j.simpleLogger.defaultLogLevel=error")
}
