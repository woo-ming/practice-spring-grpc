import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21" apply false
    id("org.springframework.boot") version "3.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("kapt") version "1.8.21"
    id("com.google.protobuf") version "0.9.3" apply false
}

group = "io.wooming"
version = "0.0.1-SNAPSHOT"

ext["grpcVersion"] = "1.55.1"
ext["protobufVersion"] = "3.20.1"
ext["coroutinesVersion"] = "1.6.2"

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    repositories {
        mavenCentral()
    }
}



subprojects {

    // gradle-nexus/publish-plugin needs these here too
    group = rootProject.group
    version = rootProject.version

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "com.google.protobuf")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")

        implementation("io.grpc:grpc-netty:1.55.1")
        implementation("io.grpc:grpc-protobuf:1.55.1")
        implementation("io.grpc:grpc-kotlin-stub:1.3.0")
        implementation("com.google.protobuf:protobuf-kotlin-lite:3.23.2")
        implementation("com.google.protobuf:protoc:3.23.2")
        implementation("io.grpc:protoc-gen-grpc-kotlin:1.3.0")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.getByName("bootJar") { enabled = false }
    tasks.getByName("jar") { enabled = true }
}