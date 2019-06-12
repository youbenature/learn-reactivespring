Spring Boot 2 migration demo based on RESTful Endpoints.

This project was created with Spring Initializer and imported into Intellij Idea.

Run main class: LearnReactivespringApplication, then from either the Postman app or through your browser type the following Get requests to test the respons from the local server:

http://localhost:8080/mono
http://localhost:8080/monolist
http://localhost:8080/flux
http://localhost:8080/fluxstream
http://localhost:8080/fluxlist
http://localhost:8080/fluxliststream

Router:
http://localhost:8080/functional/flux
http://localhost:8080/functional/mono

Added notes for creating a Spring Boot project

> How to easily start Spring Boot 2 applications
⦁	Using the Spring initializer https://start.spring.io/ for creating a Maven or Gradle project fast and easy. Or with the plugin   version of Spring Initializer in the IDE. Select the dependencies: Reactive Web (Netty Spring Webflux), Reactive MongoDB, Lombok, Embedded MongoDB (for writing integration test cases). After creating the project can be imported in the IDE, in this case as a Gradle project. Use the Import from external model and choose Gradle (Intellij IDE). (enable: use auto import, and enable create directories for empty content roots automatically).
⦁	Install Lombok plugin in the IDE and enable annotation processors in the preferences of this project.
⦁	To create non blocking api's go to the Project Reactor website and learn about reacter-core, reactor-test, reactor-netty. Java 8 is the minimum.
