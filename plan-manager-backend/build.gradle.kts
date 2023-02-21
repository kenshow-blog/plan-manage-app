import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.6.10"
    id("org.springframework.boot") version "2.7.8"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

group = "com.plan.manager"
version = "0.0.1-SNAPSHOT"
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}
val mybatisGenerator: Configuration by configurations.creating
val mybatisMapperVersion = "4.1.5"
val mybatisGeneratorVersion = "1.4.0"
val postgresqlConnectorJavaVersion = "42.3.1"
dependencies {
    mybatisGenerator(group = "org.mybatis.generator", name = "mybatis-generator-core", version = mybatisGeneratorVersion)
    mybatisGenerator(group = "org.postgresql", name = "postgresql", version = postgresqlConnectorJavaVersion)
    mybatisGenerator(group = "tk.mybatis", name = "mapper", version = mybatisMapperVersion)
}
task("mybatisGenerator") {
    doLast {
        ant.withGroovyBuilder {
            "taskdef"(
                "name" to "mbgenerator",
                "classname" to "org.mybatis.generator.ant.GeneratorAntTask",
                "classpath" to mybatisGenerator.asPath
            )
        }
        ant.withGroovyBuilder {
            "mbgenerator"(
                "overwrite" to true,
                "configfile" to "src/main/resources/generatorConfig.xml",
                "verbose" to true
            )
        }
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    // compileタスクの前に、ktlintのformatタスクを実行する
    dependsOn("ktlintFormat")
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.0")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.3.0")
    runtimeOnly("org.postgresql:postgresql:42.3.1")
    mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.0")

    implementation("org.apache.httpcomponents:httpclient:4.5.13")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    testImplementation("org.assertj:assertj-core:3.19.0")
    testImplementation("org.mockito:mockito-core:3.8.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    implementation("mysql:mysql-connector-java:8.0.23")
// 	implementation("org.springframework.boot:spring-boot-starter-security")
// 	implementation("org.springframework.session:spring-session-data-redis")
    implementation("redis.clients:jedis")
    implementation("org.springframework.boot:spring-boot-starter-aop")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
