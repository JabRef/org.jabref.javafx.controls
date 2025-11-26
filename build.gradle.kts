plugins {
    id("org.jabref.javafx.controls.gradle.base.repositories")
    id("org.jabref.javafx.controls.gradle.feature.compile")
    id("com.vanniktech.maven.publish") version "0.35.0"
}

var version: String = "0.1.0-SNAPSHOT"

mavenPublishing {
  configure(JavaLibrary(
    javadocJar = JavadocJar.Javadoc(),
    sourcesJar = true,
  ))

  publishToMavenCentral()
  signAllPublications()

  coordinates("org.jabref", "org.jabref.javafx.controls", version)

  pom {
    name.set("jablib")
    description.set("JabRef's JavaFX additions")
    inceptionYear.set("2025")
    url.set("https://github.com/JabRef/org.jabref.javafx.controls//")
    licenses {
      license {
        name.set("MIT")
        url.set("https://github.com/JabRef/org.jabref.javafx.controls//blob/main/LICENSE")
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
        url.set("https://github.com/JabRef/org.jabref.javafx.controls/")
        connection.set("scm:git:https://github.com/JabRef/org.jabref.javafx.controls/")
        developerConnection.set("scm:git:git@github.com:JabRef/org.jabref.javafx.controls/.git")
    }
  }
}

// Include the BOM in the generated POM ("inline" / "inlining")
// Source: https://github.com/gradle/gradle/issues/10861#issuecomment-3027387345
publishing.publications.withType<MavenPublication>().configureEach {
    versionMapping {
        allVariants { fromResolutionResult() }
    }
}

