FROM openjdk:8
VOLUME /tmp
ADD ./target/trds-0.0.1-SNAPSHOT.jar trds.jar
ENTRYPOINT ["java","-jar","trds.jar"]
