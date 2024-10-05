FROM openjdk:17
COPY "./target/ClubApiMongo-1.jar" "app.jar"
EXPOSE 8023
ENTRYPOINT ["java", "-jar", "app.jar"]