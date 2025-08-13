FROM openjdk:17-oracle

LABEL maintainer = "${MAINTAINER_EMAIL}"

WORKDIR /opt/app

EXPOSE 8080

COPY target/sbInventory-1.0-SNAPSHOT.jar /opt/app/sbInventory.jar

ENTRYPOINT ["java", "-jar", "sbInventory.jar"]