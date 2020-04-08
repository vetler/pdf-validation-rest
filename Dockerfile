FROM adoptopenjdk/openjdk14-openj9:jdk-14_36.1_openj9-0.19.0-alpine-slim
COPY build/libs/pdf-validation-rest-*-all.jar pdf-validation-rest.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-jar", "pdf-validation-rest.jar"]
