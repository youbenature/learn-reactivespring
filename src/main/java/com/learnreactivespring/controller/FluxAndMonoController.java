package com.learnreactivespring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;

//Endpoint flux en fluxstream zijn beide reactive, alleen Endpoint flux is blocking doordat de browser geen stream laat zien,
//maar pas ververst op het moment dat de flux klaar is, event onComplete()

@RestController
public class FluxAndMonoController {

    @GetMapping("/flux") //Create a getmapping endpoint, wanneer je in de browser /flux ingeeft, wordt de method returnflux aangeroepen //de default format type is Json
                         //A browser is a blocking client and doesnt care about flux, browser just cares about return type its getting
    public Flux<Integer> returnFlux(){ //This endpoint is going to return a Flux of 4 elements

        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
        // check the browser if the end point is returning the right values http://localhost:8080/flux
        //when you enter http://localhost:8080/flux into the browser, the browser is acting like a subscriber
        //The subcribe method is invoked

    }

    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE) //The endpoint is going to produce a Mediatype (this is a constant introduced in Spring 5.0
    public Flux<Integer> returnFluxStream(){ //This endpoint is going to return a Flux of 4 elements

        return Flux.just(1,2,3,4,5,6,7,8)
                .delayElements(Duration.ofSeconds(1))
                .log();

    }

    @GetMapping(value = "/fluxstreaminfinite", produces = MediaType.APPLICATION_STREAM_JSON_VALUE) //The endpoint is going to produce a Mediatype (this is a constant introduced in Spring 5.0
    public Flux<Long> returnFluxStreamInfinite(){ //This endpoint is going to return a Flux of 4 elements

        return Flux
                .interval(Duration.ofMillis(100))
                .log();

    }


    @GetMapping("/mono") //Create a getmapping endpoint, wanneer je in de browser /flux ingeeft, wordt de method returnflux aangeroepen //de default format type is Json
    //A browser is a blocking client and doesnt care about flux, browser just cares about return type its getting
    public Mono<Integer> returnMono() { //This endpoint is going to return a Flux of 4 elements

        return Mono.just(1)
                .log();
    }

}


//Dit is de server side
//Dit is wat er wordt gegenereerd naar aanleiding van een URI request in de browser
