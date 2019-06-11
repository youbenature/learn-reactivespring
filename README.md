# learn-reactivespring
SPRING BOOT 2 MIGRATION by Bram
The learn-reactivespring is a Demo to show how the Reactive stack works with the new Spring Boot 2
Furthermore reasons to migrate from Spring Boot 1.x to 2.0, differences, improvements and cases are described below.

> Spring Boot 2.0 whats new and differences:
⦁	Spring Boot 2 had the new Spring Framwework 5 added to it (Spring Framwork 5 came out on september 2017)
⦁	Spring Framework 5 uses Java 8, that means better readability and performance inprovemance. Spring Framework 5 introduced            Spring Webflux.
⦁	In Spring Boot 1.x you cant create a new Java 9 project, in Spring Boot 2 you can. 
⦁	The Spring Boot actuator in Spring Boot 1.x was rewritten to work with to work with the new reactive stack. 
  Spring Boot 2.0 had to work on both Reactive Stack and the older Servlet Stack. 
  Simplified security model. Improved Json structures. Simplified process for creating User-Defined Endpoints.
⦁	Good documentation for both Maven and the Gradle plugin. As well as good references and documentation for Spring Boot 2 and Project Reactor.
⦁	Spring Boot 2 Migration guide, with Spring Boot 2 many configuration properties were renamed or removed. 
  Developers can use the migration guide to help solve these things.

> Reasons when and why to use the reactive stack
⦁	When there is need for simple End point configuration and non blocking data streams
⦁	When there is alot of data travel
⦁	With reactive programming the idea is to move away from the "Thread per request Model" and handle a higher load of users with less threads. 
  The traditional REST Api design (used in the Servlet stack) is blocking and sychronous.
⦁	To use less computer resources when the application is scaling up according to more users. 
  (no blocks are waiting and therefore no unnessesary waiting threads when large amout of data needs to be pulled. 
  Flux can stream the data like a chain, without the need for waiting.
⦁	Therefore the responstime or latency is very short (client <-> database), much shorter that traditional blocking approach. 
  As well as less memory usage per user due to data stream vs big amount of data being stored temporary.
⦁	When the application uses many micro services like a webshop, IOT or other distrubeted systems.

> How to easily start Spring Boot 2 applications
⦁	Using the Spring initializer https://start.spring.io/ for creating a Maven or Gradle project fast and easy. 
  Or with the plugin version of Spring Initializer in the IDE. Select the dependencies: Reactive Web (Netty Spring Webflux), Reactive MongoDB, Lombok, Embedded MongoDB (for writing integration test cases). 
  After creating the project can be imported in the IDE, in this case as a Gradle project. Use the Import from external model and choose Gradle (Intellij IDE). (enable: use auto import, and enable create directories for empty content roots automatically).
⦁	Install Lombok plugin in the IDE and enable annotation processors in the preferences of this project.
⦁	To create non blocking api's go to the Project Reactor website and learn about reacter-core, reactor-test, reactor-netty. Java 8 is the minimum.

Github project link: https://github.com/youbenature/learn-reactivespring


