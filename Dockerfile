FROM openjdk:11
EXPOSE 8080
ADD target/spring-boot-blogapp-1.0.jar spring-boot-blogapp-1.0.jar
ENTRYPOINT ["java","-jar","/spring-boot-blogapp-1.0.jar"]