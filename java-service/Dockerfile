FROM maven:3.8-jdk-11
RUN mkdir -p /java-service
WORKDIR /java-service
ENTRYPOINT ["mvn", "--batch-mode", "spring-boot:run"]
# ENTRYPOINT [ "sleep","10000" ]