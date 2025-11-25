plugins {
    id("org.gradlex.extra-java-module-info")
    id("org.gradlex.jvm-dependency-conflict-resolution")
    id("org.gradlex.java-module-dependencies")
}

jvmDependencyConflicts {
    consistentResolution {
        platform(":versions")
    }
}

// Tell gradle which jar to use for which platform
// Source: https://github.com/jjohannes/java-module-system/blob/be19f6c088dca511b6d9a7487dacf0b715dbadc1/gradle/plugins/src/main/kotlin/metadata-patch.gradle.kts#L14-L22
jvmDependencyConflicts.patch {
    listOf(
        "base", "controls", "graphics"
    ).forEach { jfxModule ->
        module(
            "org.openjfx:javafx-$jfxModule"
        ) {
            addTargetPlatformVariant(
                "",
                "none",
                "none"
            ) // matches the empty Jars: to get better errors
            addTargetPlatformVariant(
                "linux",
                OperatingSystemFamily.LINUX,
                MachineArchitecture.X86_64
            )
            addTargetPlatformVariant(
                "linux-aarch64",
                OperatingSystemFamily.LINUX,
                MachineArchitecture.ARM64
            )
            addTargetPlatformVariant(
                "mac",
                OperatingSystemFamily.MACOS,
                MachineArchitecture.X86_64
            )
            addTargetPlatformVariant(
                "mac-aarch64",
                OperatingSystemFamily.MACOS,
                MachineArchitecture.ARM64
            )
            addTargetPlatformVariant(
                "win",
                OperatingSystemFamily.WINDOWS,
                MachineArchitecture.X86_64
            )
        }
    }
}
extraJavaModuleInfo {
    failOnAutomaticModules = true
    failOnModifiedDerivedModuleNames = true
    skipLocalJars = true


}
