plugins {
    id("org.gradlex.extra-java-module-info")
    id("org.gradlex.jvm-dependency-conflict-resolution")
    id("org.gradlex.java-module-dependencies") // only for mappings at the moment
}

javaModuleDependencies {
    // TODO remove to translate 'requires' from 'module-info.java' to Gradle dependencies
    //      and remove 'dependencies {}' block from build.gradle files
    analyseOnly = true
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
        "base",
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

    module("org.openjfx:javafx-base", "javafx.base") {
        patchRealModule()
        exportAllPackages()
    }
}
