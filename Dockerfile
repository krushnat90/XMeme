#
# MAVEN BUILD
#
FROM maven:3.5-jdk-8-alpine AS BUILD
COPY xmeme-backend/src /home/app/src
COPY xmeme-backend/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

  
#
# JAVA SERVER START
#
FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/XMeme-1.0.jar /usr/local/lib/xmeme.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/xmeme.jar"]