FROM eclipse-temurin:21-jdk-alpine

COPY target/control1-0.0.1-SNAPSHOT.jar app-1.0.0.jar

ENTRYPOINT [ "java", "-jar", "app-1.0.0.jar" ]
