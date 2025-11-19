plugins {
    id("org.jabref.gradle.module")
    id("java-library")
    id("com.vanniktech.maven.publish") version "0.35.0"

}


dependencies {
    // api(platform(project(":versions")))

    implementation("org.openjfx:javafx-base")
}

mavenPublishing {
    configure(JavaLibrary(
        // configures the -javadoc artifact, possible values:
        // - `JavadocJar.None()` don't publish this artifact
        // - `JavadocJar.Empty()` publish an emprt jar
        // - `JavadocJar.Javadoc()` to publish standard javadocs
        javadocJar = JavadocJar.Javadoc(),
        // whether to publish a sources jar
        sourcesJar = true,
    ))

    publishToMavenCentral()
    signAllPublications()

    coordinates("org.jabref", "controls", version)

    pom {
        name.set("jablib")
        description.set("JabRef's Java library to work with BibTeX")
        inceptionYear.set("2025")
        url.set("https://github.com/JabRef/jabref/")
        licenses {
            license {
                name.set("MIT")
                url.set("https://github.com/JabRef/jabref/blob/main/LICENSE")
            }
        }
        developers {
            developer {
                id.set("jabref")
                name.set("JabRef Developers")
                url.set("https://github.com/JabRef/")
            }
        }
        scm {
            url.set("https://github.com/JabRef/jabref")
            connection.set("scm:git:https://github.com/JabRef/jabref")
            developerConnection.set("scm:git:git@github.com:JabRef/jabref.git")
        }
    }
}
