package com.learnreactivespring.fluxandmonoplayground;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/*    PROJECT REACTOR is the place to go if you want to use FLUX and MONO
    https://projectreactor.io/
    Hier kun je alles vinden
    https://projectreactor.io/docs/core/release/reference/
    For building the non blocking RESTful Reactive API's we need to learn the following module's:
    reacter-core
    reactor-test
    reactor-netty
    The two classe Flux and Mono are the only classes you need to know.
    These are the reactive types of project Reactor
    Flux is a class wich represents 0 to N elements
    Mono is a class that represents 0-1 element
    Hieronder staan een aantal Unit tests

    FLUX TESTEN MET DE TERMINAL
    */


public class FluxAndmonoTest {

    /*@Test
    public void fluxTest(){

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring"); //Later this Flux will flow from Database but for now we keep it simple, we are building this Flux.
        //This is a flux of type string

        //the only way to access the elements from the Flux is by subscribing to it
        stringFlux.subscribe(System.out::println); //With this you are actively attaching a subcriber that is going to read all the values from the flux

        //When you subcribe, thats when the Flux is start emitting the values to the subscriber
        //The subscribe method has many overload methods, but for now we are going to use only the System.out
        //We are reading the values from the Flux and printing them
    }
} */

    @Test
    public void fluxTest() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                /*.concatWith(Flux.error(new RuntimeException("Exception Occured")))*/ //Later this Flux will flow from Database but for now we keep it simple, we are building this Flux.
                .concatWith(Flux.just("After Error"))
                .log();
        //This is a flux of type string
        //the only way to access the elements from the Flux is by subscribing to it
        stringFlux.subscribe(System.out::println,
                   (e) -> System.err.print("Exception is " + e)
                  , () -> System.out.print("Completed"));
                //, () -> next()); //This part is being executed whenever there is a complete event, onComplete(), from the Flux.
                // () is a runnable lambda expression


        //With this you are actively attaching a subcriber that is going to read all the values from the flux
        //When you subcribe, thats when the Flux is start emitting the values to the subscriber
        //The subscribe method has many overload methods, but for now we are going to use only the System.out
        //We are reading the values from the Flux and printing them
    }

    @Test
    public void fluxTestElements_WithError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                //We are going to test the elements in the Flux and the order they are flowing to the subscriber
                //Reactor test is a module and it has a class called StepVerifier
                .concatWith(Flux.error(new RuntimeException("Exception Occured check Flux flow")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                //.expectError(RuntimeException.class)//Hier wordt gekeken naar de error die je hierboven hebt aangemaakt voor het testen,
                                                    // deze error is van het type RuntimeException, door deze als class parameter in te voegen verwacht de StepVerifier dat de error van het type RuntimeException is.
                .expectErrorMessage("Exception Occured check Flux flow")//Hiermee kun je de message binnen de exception checken
                //.verifyComplete(); //na een error kan er geen complete event komen
                .verify();
    }


    @Test
    public void fluxTestElementsCount_WithError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                //We are going to test the elements in the Flux and the order they are flowing to the subscriber
                //Reactor test is a module and it has a class called StepVerifier
                .concatWith(Flux.error(new RuntimeException("Exception Occured check Flux flow")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                //.expectError(RuntimeException.class)//Hier wordt gekeken naar de error die je hierboven hebt aangemaakt voor het testen,
                // deze error is van het type RuntimeException, door deze als class parameter in te voegen verwacht de StepVerifier dat de error van het type RuntimeException is.
                .expectErrorMessage("Exception Occured check Flux flow")//Hiermee kun je de message binnen de exception checken
                //.verifyComplete(); //na een error kan er geen complete event komen
                .verify();
    }

    @Test
    public void fluxTestElementsInOneRow_WithError(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring Boot", "Reactive Spring")
                .verifyComplete();
    }


    @Test
    public void monoTest(){
       Mono<String> stringMono = Mono.just("Spring");

        StepVerifier.create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete();


    }

    @Test
    public void monoTestError(){

        StepVerifier.create(Mono.error(new RuntimeException("Exception Created")).log())
                .expectError(RuntimeException.class)
                .verify();


    }

    /*public void next(){
    System.out.print(" FLUX IS FINISHED WITHOUT ERRORS, COMPLETED RUN NEW METHOD");
    }*/




}