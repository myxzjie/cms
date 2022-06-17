FROM openjdk:8u232-jre

MAINTAINER Vito 513961835@qq.com

RUN mkdir -p /cms-boot-web

WORKDIR /cms-boot-web
VOLUME /tmp

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/{TZ} /etc/localtime && echo "{TZ}" > /etc/timezone

COPY ./target/cms-boot-web-1.1.0.jar /cms-boot-web/app.jar
#ADD agent/ /agent
CMD ["java", "-version"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cms-boot-web/app.jar"]
#ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=febs-gateway", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/febs/febs-gateway-1.4-RELEASE.jar"]
