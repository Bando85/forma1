

# Formula One Teams Application

The application provides all the funcionality to manage Formula One Teams.

The website is mobile-friendly.

## Live server

The application is already deployed and running on an AWS Lightsail instance. 

**You can test it here:**  
**http://18.194.91.62:8080**

## Configuration

The application requires Java 19, you can download it from [here](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html "Oracle JDK 17 website").

To build the application, you need to install npm in the [app](app) directory first.

`npm install`

Then build the frontend with the following command:

`npm run build`
 
After that please use Gradle with the following commands depending on your system in the [root directory](/../../) to build the project and package it to a jar:

`bash gradlew build`

`gradlew.bat build`

The application is running on port 8080 by default. You can modify the port number in [application.properties](src/main/resources/application.properties) by adding server.port to it (e.g. server.port=80).

## How to run the application

You can run the application with the following command from the [root directory](/../../):

`java -jar build/libs/forma1-0.0.1-SNAPSHOT.jar`

## Functions

The application stores details about Formula One Teams. Registered users are able to create, modify and delete teams as well as list them. 

Guest users can only access the teams list.