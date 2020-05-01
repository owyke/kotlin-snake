import org.gradle.jvm.tasks.Jar

plugins {
    kotlin("jvm") version "1.3.72"
}


group = "se.wyko"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.javalin:javalin:3.8.0")
    implementation("org.slf4j:slf4j-simple:1.7.28")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")


}


val fatJar = task("fatJar", type = Jar::class) {
    baseName = "fat-snake"
    manifest {
        attributes["Main-Class"] = "se.wyko.snake.MainKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
