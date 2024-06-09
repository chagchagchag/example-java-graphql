plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "io.chagchagchag.graphql"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// security (강사님이 추가해두긴 했는데, 초반에는 disable 해두기로 결정)
	implementation("org.springframework.boot:spring-boot-starter-security")

	// graphql-scalars
	implementation("com.graphql-java:graphql-java-extended-scalars:21.0")

	// h2
	runtimeOnly("com.h2database:h2")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// webflux test
	testImplementation("org.springframework:spring-webflux")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
