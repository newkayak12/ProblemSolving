plugins {
    kotlin("jvm") version "1.9.10"
    application
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