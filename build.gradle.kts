plugins {
    id("org.jabref.javafx.controls.gradle.base.repositories")
    id("org.jabref.javafx.controls.gradle.feature.compile")

    id("maven-publish")
    id("signing")
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

group = "org.jabref"
var version: String = "0.1.0-SNAPSHOT"
val isReleaseVersion = !version.endsWith("SNAPSHOT")

java {
	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "javafx.controls"
            from(components["java"])
            pom {
                name = "org.jabref.javafx.controls"
                description = "JabRef's JavaFX additions"
                url = "https://github.com/JabRef/org.jabref.javafx.controls"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                        distribution = "repo"
                    }
                }
                developers {
                    developer {
                        id = "Siedlerchr"
                    }
                }
                scm {
                    url = "https://github.com/JabRef/org.jabref.javafx.controls"
                    connection = "scm:git:git://github.com/JabRef/org.jabref.javafx.controls.git"
                    developerConnection = "scm:git:git@github.com:JabRef/org.jabref.javafx.controls.git"
                }
            }
        }
    }
}

signing {
    isRequired = isReleaseVersion
    useInMemoryPgpKeys(System.getenv("SIGNING_KEY"), System.getenv("SIGNING_PASSWORD"))
    sign(publishing.publications["mavenJava"])
}

nexusPublishing {
    repositories {
        sonatype {
            username = System.getenv("OSSRH_USERNAME")
            password = System.getenv("OSSRH_TOKEN")
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
        }
    }
}
