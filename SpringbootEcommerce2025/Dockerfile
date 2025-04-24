#Use a JDK base image
From openjdk:17-jdk-slim

#set environment variables
ENV JAVA_OPTS=""
ENV PORT 8080
EXPOSE 8080

#Add a volume pointing to /tmp
#VOLUME /tmp

# Set the working directory
WORKDIR /app

#Copy the built jar into the container
COPY target/*.jar app.jar





#Run the jar file
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS  -jar app.jar"]