FROM openjdk:8u232-jre

MAINTAINER Vito 513961835@qq.com

RUN mkdir -p /cms-boot-web

WORKDIR /cms-boot-web
VOLUME /tmp

COPY ./target/cms-boot-web-1.0.7.jar /cms-boot-web/app.jar
#ADD agent/ /agent
CMD ["java", "-version"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cms-boot-web/app.jar"]
#ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=febs-gateway", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/febs/febs-gateway-1.4-RELEASE.jar"]
