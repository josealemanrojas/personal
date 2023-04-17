FROM maven:3.8.3-openjdk-17
RUN mkdir -p /kotlin-service
WORKDIR /kotlin-service
ENTRYPOINT ["mvn", "--batch-mode", "spring-boot:run"]
# ENTRYPOINT [ "sleep","10000" ]