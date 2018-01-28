FROM relateiq/oracle-java8:latest
MAINTAINER BuHuan Huang <i@buhuan.me>

ENV APP_HOME=/usr/bin/app
ADD target/http-server.jar $APP_HOME/http-server.jar
WORKDIR $APP_HOME

EXPOSE 8081
ENTRYPOINT ["/bin/sh", "-c", "java -jar http-server.jar"]