import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.20"
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("test"))
    implementation("org.junit.jupiter:junit-jupiter:5.10.0")
    implementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.1.11")
}

tasks.test {
    useJUnitPlatform()
}