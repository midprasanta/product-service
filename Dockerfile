FROM openjdk:8-jdk-alpine as builder
RUN  mkdir -p /workdir
COPY . /workdir
WORKDIR /workdir
RUN ./mvnw clean package

FROM openjdk:8-jdk-alpine as runtime
COPY --from=builder  /workdir/target/*.jar /app.jar
CMD ["java", "-jar", "-Dspring.datasource.url=jdbc:postgresql://postgres:5432/spring_boot_simple", "/app.jar"]
