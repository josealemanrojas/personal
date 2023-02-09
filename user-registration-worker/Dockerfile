FROM maven:3.8-jdk-11
RUN mkdir -p /user-registration-worker
WORKDIR /user-registration-worker
ENTRYPOINT ["mvn", "--batch-mode", "spring-boot:run"]
# ENTRYPOINT [ "sleep","10000" ]