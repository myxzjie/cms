FROM openjdk:8u232-jre

#MAINTAINER docker_maven docker_maven@email.com
RUN mkdir -p /cms-app

WORKDIR /cms-app
VOLUME /tmp

COPY ./target/cms-boot-web-0.0.1-SNAPSHOT.jar /cms-app/app.jar
#ADD agent/ /agent
CMD ["java", "-version"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cms-app/app.jar"]
#ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=febs-gateway", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/febs/febs-gateway-1.4-RELEASE.jar"]
