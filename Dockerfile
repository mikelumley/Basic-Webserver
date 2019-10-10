FROM openjdk:14-alpine
WORKDIR /app/src/main/java
COPY . /app
RUN javac controller/Server.java
CMD ["java", "controller/Server", "0.0.0.0", "80"]