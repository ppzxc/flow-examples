package org.example.reactive.flux;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxPractice08 {

    public static void main(String[] args) {
        Flux<String> firstPublisher = Flux.range(1, 100)
                .map(integer -> "first " + integer)
                .delayElements(Duration.ofMillis(5));

        Flux<String> secondPublisher = Flux.range(1, 100)
                .map(integer -> "second " + integer)
                .delayElements(Duration.ofMillis(22));

        Flux<String> thirdPublisher = Flux.range(1, 100)
                .map(integer -> "third " + integer)
                .delayElements(Duration.ofMillis(47));

        Disposable disposable = Flux.zip(firstPublisher, secondPublisher, thirdPublisher)
                .doOnNext(System.out::println)
                .subscribe();

        disposable.dispose();
    }
}
