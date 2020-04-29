plugins {
    kotlin("jvm") version "1.3.72"
}

group = "se.wyke"
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

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
