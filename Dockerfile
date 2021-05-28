FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/football-league.jar

# cd /opt/app
WORKDIR /opt/app
COPY ${JAR_FILE} football-league.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","football-league.jar"]


# sudo docker build -t football:1.0 .
# sudo docker run -d -p 8085:8085 -t football:1.0
