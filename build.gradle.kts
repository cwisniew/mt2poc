import org.ajoberstar.grgit.Grgit


val moduleName: String by extra;
val vendor: String by extra;

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
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15

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
    implementation("com.google.inject.extensions", "guice-assistedinject", "4.2.2");
    implementation("com.github.JamzTheMan", "DockFX", "MapTool-SNAPSHOT")
    implementation("org.kordamp.ikonli", "ikonli-zondicons-pack", "11.3.4")




    // Use JUnit test framework
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
    testImplementation("org.mockito:mockito-core:2.28.2")
    testImplementation("org.mockito:mockito-junit-jupiter:2.28.2")
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
                "Implementation-Title" to "Renderer POC",
                "Implementation-Version" to tagVersion,
                "Main-Class" to "net.rptools.maptool.App"
        )
    }
}


tasks.withType<Test> {
    jvmArgs("--enable-preview", "-noverify")
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}



jacoco {
    toolVersion = "0.8.4"
}

tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}


tasks {
    withType<JavaCompile>().configureEach {
        options.compilerArgs.add("--enable-preview")
        options.compilerArgs.add("-Xlint:unchecked")
        options.compilerArgs.add("-Xlint:deprecation")
        //options.compilerArgs.add("-Xlint:preview")
    }
}

application {
    // Define the main class for the application
    mainClassName = "net.rptools.maptool.App"
    applicationDefaultJvmArgs = listOf("--enable-preview", "--add-opens", "java.base/java.lang=com.google.guice")
}
