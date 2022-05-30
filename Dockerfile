FROM openjdk:17

COPY ./build/libs/sale-api-0.0.1-SNAPSHOT.jar sale-api-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "sale-api-0.0.1-SNAPSHOT.jar"]
