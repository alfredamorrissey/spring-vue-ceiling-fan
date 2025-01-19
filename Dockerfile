FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/spring-vue-0.0.1-SNAPSHOT.jar spring-vue.jar
ENTRYPOINT ["java", "-jar", "/spring-vue.jar"]
