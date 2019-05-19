import org.ajoberstar.grgit.Grgit




val moduleName: String by project;
val vendor: String by project;

val currentOS = org.gradle.internal.os.OperatingSystem.current()

var revision: String = "";
var sentryDSN: String = "";
var environment: String = "";
var tagVersion: String = "";

plugins {
    java
    application
    eclipse
    jacoco
    id("com.diffplug.gradle.spotless") version "3.18.0"
    id("org.openjfx.javafxplugin") version "0.0.5"
    id("org.ajoberstar.grgit") version "3.1.1"

}

repositories {
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
    targetCompatibility = JavaVersion.VERSION_12

    // Get tag and commit info from Git to use for version numbering
    var repo = Grgit.open(mapOf("currentDir" to project.rootDir))
    var head = repo.head()
    var tags = repo.tag.list().find {
        it.commit == head
    }

    revision = head.abbreviatedId
    var revisionFull = head.id

    if (tags != null) {
        tagVersion = tags.getName()
        environment = "Production"
    } else {
        tagVersion = "SNAPSHOT-" + revision
        environment = "Development"
    }

    // not doing anything as of a bug in grgit when opening with currentDir/dir
    // https://github.com/ajoberstar/grgit/issues/288
    repo.close();
    // therefore close the underlying repository manually
    repo.repository.jgit.close();

    // vendor, tagVersion, msiVersion, and DSN's defaults are set in gradle.properties
    println("Configuring for ${project.name} $tagVersion by $vendor for $currentOS")
    //println 'Configuring for ' + project.name + " " + tagVersion + " by " + vendor + " for " + currentOS
}


dependencies {
    implementation("com.google.guava:guava:27.0.1-jre")
    implementation("org.reflections", "reflections", "0.9.11")
    implementation("com.jfoenix", "jfoenix", "9.0.8")
    implementation("org.apache.logging.log4j", "log4j-api", "2.11.2")
    implementation("org.apache.logging.log4j", "log4j-core", "2.11.2")
    implementation("com.google.code.gson", "gson", "2.8.5")
    implementation("com.google.inject", "guice", "4.2.2")
    implementation("com.github.JamzTheMan", "DockFX", "MapTool-SNAPSHOT")
    implementation("com.github.almasb", "fxgl", "11.2-beta");



    // Use JUnit test framework
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
}


javafx {
    version = "12.0.1"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}


spotless {

    java {
        target("src/**/*.java")
        licenseHeaderFile(file("spotless.license.java"))
        googleJavaFormat()
        // https://github.com/diffplug/spotless/blob/master/PADDEDCELL.md
        // paddedCell()
    }

    format("misc") {
        target("**/*.gradle", "**/.gitignore")

        // spotless has built-in rules for most basic formatting tasks
        trimTrailingWhitespace()
        // or spaces. Takes an integer argument if you don't like 4
        indentWithSpaces(4)
        // https://github.com/diffplug/spotless/blob/master/PADDEDCELL.md
        // paddedCell()
    }
}


tasks.jar {
    manifest {
        attributes(
                "Implementation-Title" to "MapTool 2",
                "Implementation-Version" to tagVersion,
                "Main-Class" to "net.rptools.maptool.App"
        )
    }
}


application {
    // Define the main class for the application
    mainClassName = "net.rptools.maptool.App"
    applicationDefaultJvmArgs = listOf("--add-opens", "java.base/java.lang=com.google.guice")

}
