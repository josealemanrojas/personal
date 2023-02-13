FROM maven:3.8-jdk-11
RUN mkdir -p /user-notification-worker
WORKDIR /user-notification-worker
ENTRYPOINT ["mvn", "--batch-mode", "spring-boot:run"]
# ENTRYPOINT [ "sleep","10000" ]