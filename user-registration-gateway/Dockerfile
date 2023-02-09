FROM maven:3.8-jdk-11
RUN mkdir -p /user-registration-gateway
WORKDIR /user-registration-gateway
ENTRYPOINT ["mvn", "--batch-mode", "spring-boot:run"]
# ENTRYPOINT [ "sleep","10000" ]