#FROM eclipse-temurin:11-jdk-alpine
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#apple silicon issue
#FROM --platform=linux/amd64 python:3.7-alpine
#ENV PATH=${PATH}:/usr/lib/jvm/java-11-openjdk-amd64/bin/java

#EXPOSE 80
FROM gradle
WORKDIR /build
#COPY . /build
# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle.kts settings.gradle.kts /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true
# 빌더 이미지에서 애플리케이션 빌드
COPY . /build
RUN gradle build -x test --parallel

#RUN ./gradlew build
#RUN  gradle clean build --no-daemon

FROM openjdk:11
WORKDIR /app
# 빌더 이미지에서 jar 파일만 복사
COPY /build/libs/app.jar .
EXPOSE 80
RUN groupadd -g 999 ubuntu
RUN useradd -r -u 999 -g ubuntu ubuntu
USER ubuntu
ENTRYPOINT ["java", "-jar", "app.jar"]


