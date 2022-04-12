plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(project(":"))

    testImplementation("org.junit.platform:junit-platform-suite:1.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")

    testImplementation("io.cucumber:cucumber-java:7.2.3")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.2.3")
}

tasks {

    test {
        useJUnitPlatform()
        systemProperties(project.gradle.startParameter.systemPropertiesArgs)
        systemProperty("cucumber.plugin", "html:build/reports/cucumber.html")
        systemProperty("cucumber.execution.parallel.enabled", "true")
        systemProperty("cucumber.execution.parallel.config.fixed.parallelism", "8")

        outputs.upToDateWhen { false }
    }

}