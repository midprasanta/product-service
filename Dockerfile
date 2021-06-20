FROM openjdk:8-jdk-alpine as builder
RUN  mkdir -p /workdir
COPY . /workdir
WORKDIR /workdir
RUN ./mvnw clean package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine as runtime
COPY --from=builder  /workdir/target/*.jar /app.jar
CMD ["java", "-jar", "-Dspring.datasource.url=jdbc:postgresql://postgres:5432/product-service-db","-Dspring.redis.host=redis","-Dspring.redis.port=6379" ,"/app.jar"]