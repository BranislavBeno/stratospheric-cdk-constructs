plugins {
    java
    application
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
}

dependencies {
  implementation("software.amazon.awscdk:aws-cdk-lib:2.88.0")
  implementation("software.constructs:constructs:10.2.69")
  implementation(platform("com.fasterxml.jackson:jackson-bom:2.15.2"))
  implementation("com.fasterxml.jackson.core:jackson-core")
  implementation("com.fasterxml.jackson.core:jackson-databind")
  testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
  testImplementation("org.assertj:assertj-core:3.24.2")
}

version = "0.14.0-Alpha"

tasks.test {
    useJUnitPlatform()
    afterSuite(
        KotlinClosure2<TestDescriptor, TestResult, Unit>({ descriptor, result ->
            if (descriptor.parent == null) {
                logger.lifecycle(
                    "\nTest result: ${result.resultType}",
                )
                logger.lifecycle(
                    "Test summary: " +
                            "${result.testCount} tests, " +
                            "${result.successfulTestCount} succeeded, " +
                            "${result.failedTestCount} failed, " +
                            "${result.skippedTestCount} skipped",
                )
            }
        }),
    )
}
