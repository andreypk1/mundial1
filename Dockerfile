FROM eclipse-temurin:17-jdk

COPY "./target/mundial-1.jar" "app.jar"

EXPOSE 8221

ENTRYPOINT ["java", "-jar", "app.jar"]
