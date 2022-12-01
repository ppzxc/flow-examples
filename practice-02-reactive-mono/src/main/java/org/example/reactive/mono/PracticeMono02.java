package org.example.reactive.mono;

import reactor.core.publisher.Mono;

public class PracticeMono02 {
    public static void main(String[] args) {
        Mono.just(1)
                .flatMap(input -> Mono.just(input + 1))
                .flatMap(input -> Mono.just(input + 1))
//                .flatMap(input -> Mono.just(input + 3))
//                .flatMap(input -> Mono.just(input + 3))
                .flatMap(input -> Mono.just(new RuntimeException("3")))
//                .flatMap(input -> Mono.just(input + 4))
//                .flatMap(input -> Mono.just(input + 5))
                .log()
//                .onErrorMap()
                .block();
    }
}
