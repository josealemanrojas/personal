FROM maven:3.8-jdk-11
RUN mkdir -p /post-service
WORKDIR /post-service
ENTRYPOINT ["mvn", "--batch-mode", "spring-boot:run"]
# ENTRYPOINT [ "sleep","10000" ]