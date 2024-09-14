FROM maven:3.8.4-openjdk-17 AS builder

   # Установите рабочую директорию
WORKDIR /app
   # Скопируйте pom.xml и все модули
COPY pom.xml .
COPY common/pom.xml common/
COPY common/src common/src
COPY delivery-service/pom.xml delivery-service/
COPY delivery-service/src delivery-service/src
COPY kitchen-service/pom.xml kitchen-service/
COPY kitchen-service/src kitchen-service/src
COPY migration/pom.xml migration/
COPY migration/src migration/src
COPY notificaction-service/pom.xml notificaction-service/
COPY notificaction-service/src notificaction-service/src
COPY order-service/pom.xml order-service/
COPY order-service/src order-service/src
#COPY order-service/src/main/resources/application.properties order-service/
#COPY order-service/src/main/resources order-service/src/main/resources

RUN mvn clean package

   # Используйте другой образ для выполнения приложения
FROM openjdk:17-jdk-slim
WORKDIR /app

   # Скопируйте собранный JAR из предыдущего этапа
COPY --from=builder /app/common/target/common-0.0.1-SNAPSHOT.jar ./common-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/delivery-service/target/delivery-service-0.0.1-SNAPSHOT.jar ./delivery-service-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/migration/target/migration-0.0.1-SNAPSHOT.jar ./migration-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/kitchen-service/target/kitchen-service-0.0.1-SNAPSHOT.jar ./kitchen-service-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/notificaction-service/target/notificaction-service-0.0.1-SNAPSHOT.jar ./notificaction-service-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/order-service/target/order-service-0.0.1-SNAPSHOT.jar ./order-service-0.0.1-SNAPSHOT.jar


   # Укажите команду для запуска приложения
CMD ["java", "-jar", "delivery-service-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "migration-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "kitchen-service-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "notificaction-service-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]


