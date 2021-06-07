FROM adoptopenjdk/openjdk14
MAINTAINER dmytro.malohlovets
COPY target/springbootapp-0.0.1-SNAPSHOT.jar message-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]