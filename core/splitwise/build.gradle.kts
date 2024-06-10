plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.7"
    kotlin("plugin.serialization") version "1.9.10"
    id("org.jetbrains.kotlin.kapt") version "1.9.22"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

application {
    mainClass.set("Main.kt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    testImplementation("io.ktor:ktor-server-tests-jvm")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.22")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.7")
    implementation ("io.ktor:ktor-serialization:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")

    implementation ("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation( "org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation ("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("org.postgresql:postgresql:42.3.1")
    implementation ("com.zaxxer:HikariCP:5.0.0")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.google.dagger:dagger:2.50")
    annotationProcessor("com.google.dagger:dagger-compiler:2.50")
    kapt("com.google.dagger:dagger-compiler:2.50")
}

//tasks.test {
//    useJUnitPlatform()
//}
kotlin {
    jvmToolchain(11)
}
