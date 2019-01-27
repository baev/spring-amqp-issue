import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.springframework.boot.gradle.dsl.SpringBootExtension

buildscript {
    repositories {
        mavenLocal()
        jcenter()
        maven("http://repo.spring.io/plugins-release")
        maven("https://plugins.gradle.org/m2/")
    }
}

tasks.existing(Wrapper::class) {
    gradleVersion = "4.10.2"
}

group = "org.example"
version = version

plugins {
    id("java")
    id("org.springframework.boot") version "2.1.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

tasks.withType(JavaCompile::class) {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
    options.encoding = "UTF-8"
}

configure<SpringBootExtension> {
    buildInfo()
}

configure<DependencyManagementExtension> {
    dependencies {
        dependency("com.fasterxml.jackson.core:jackson-databind:2.9.8")
        dependency("org.projectlombok:lombok:1.18.4")
    }
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compile("org.springframework.boot:spring-boot-devtools")
    compile("org.springframework.boot:spring-boot-starter-amqp")
    compile("com.fasterxml.jackson.core:jackson-databind")
    compileOnly("org.projectlombok:lombok")
}

repositories {
    mavenLocal()
    jcenter()
}

