package com.learnreactivespring.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component //will be scanned and available as a Bean in Spring container
public class SampleHandlerFuncion {

    public Mono<ServerResponse> flux(ServerRequest sr){

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Flux.just(1,2,3,4,5,6,7,8)
                        .log(),Integer.class
                );
    }

    public Mono<ServerResponse> mono(ServerRequest sr){

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Mono.just(1)
                                .log(),Integer.class
                );
    }

}
