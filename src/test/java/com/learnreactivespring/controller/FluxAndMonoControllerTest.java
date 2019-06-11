package com.learnreactivespring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebFluxTest //Annotation got intruduced with Spring 5. //Its going to scan all the classes annotated with @RestController, @Controller etc
//WebFluxTest is not going to scan classes wtih @Component, @Service or @Repository
//WebFluxTest will create an instance of the webtestclient
public class FluxAndMonoControllerTest {

    //Hier maak je een test client aan die ook op dezelfde manier een URI (/flux) gebruikt om de waardes van de flux te verifieren
    @Autowired //In order to write the test cases for the none blocking Endpoint we need a non blocking Client
    WebTestClient webTestClient; // This is the subscriber

    @Test
    public void flux_approach1(){ //the endpoint that we are writing a test case for is a Flux
        Flux<Integer> integerFlux = webTestClient.get().uri("/flux") // connect (get) with the endpoint called /flux
                // URI is an endpoint. A URL works the same, GET https://myste.com/api/users, GET https://myste.com/api/users/1 You can use different requests on the same URL
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange() //invoke endpoint
                .expectStatus().isOk() //check if status is ok
                .returnResult(Integer.class) //we expect a type return as integer
                .getResponseBody(); //in this body we will get the actual flux

        StepVerifier.create(integerFlux) //Evaluate the values from the flux
            .expectSubscription() //we expect the subscription to be send to us over the network from this endpoint
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectNext(4)
            .verifyComplete();
    }

    @Test
    public void flux_approach2() { //the endpoint that we are writing a test case for is a Flux
        webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Integer.class)
                .hasSize(4);

    }

    @Test
    public void flux_approach3() { //the endpoint that we are writing a test case for is a Flux

        List<Integer> expectedIntegerList = Arrays.asList(1,2,3,4);

        EntityExchangeResult<List<Integer>> entityExchangeResult =  webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Integer.class)
                .returnResult();

        assertEquals(expectedIntegerList,entityExchangeResult.getResponseBody());
    }

    @Test
    public void flux_approach4() { //the endpoint that we are writing a test case for is a Flux

        List<Integer> expectedIntegerList = Arrays.asList(1,2,3,4);

        webTestClient
                .get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Integer.class)
                .consumeWith((response) -> {
                    assertEquals(expectedIntegerList,response.getResponseBody());
                        });

    }

    @Test
    public void fluxStream(){
        Flux<Long> longStreamFlux = webTestClient.get().uri("/fluxstreaminfinite") // connect (get) with the endpoint called /flux
                // URI is an endpoint. A URL works the same, GET https://myste.com/api/users, GET https://myste.com/api/users/1 You can use different requests on the same URL
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange() //here the data is starting to flow to this client, invoke endpoint
                .expectStatus().isOk() //check if status is ok
                .returnResult(Long.class) //we expect a type return as integer
                .getResponseBody(); //in this body we will get the actual flux

        StepVerifier.create(longStreamFlux)
                .expectNext(0l) //zero L
                .expectNext(1l) //one L
                .expectNext(2l) //two L
                .thenCancel()
                .verify();

    }

    @Test
    public void mono(){

        Integer expectedValue = new Integer(1);

        webTestClient.get().uri("/mono") // connect (get) with the endpoint called /flux
                // URI is an endpoint. A URL works the same, GET https://myste.com/api/users, GET https://myste.com/api/users/1 You can use different requests on the same URL
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange() //here the data is starting to flow to this client, invoke endpoint
                .expectStatus().isOk() //check if status is ok
                .expectBody(Integer.class) //we expect a type return as integer
                .consumeWith((response) -> {
                    assertEquals(expectedValue, response.getResponseBody());
                }); //in this body we will get the actual flux



    }


}


//Dit zijn client side tests