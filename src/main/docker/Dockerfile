FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD lazybee-1.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
RUN apk --update add tzdata
ENV TZ=Asia/Bangkok
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]