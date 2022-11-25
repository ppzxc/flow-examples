package org.example.reactive.flux;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

@Slf4j
public class FluxPractice06 {

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

        Flux.merge(firstPublisher, secondPublisher, thirdPublisher)
                .flatMap(input -> {
                  log.info("first [{}]", input);
                  return Mono.just(input);
                })
                .flatMap(input -> {
                  log.info("second [{}]", input);
                  if (input.contains("90")) {
//                    throw new IllegalArgumentException("something throwing " + input);
                    return Mono.error(new IllegalArgumentException("something throwing " + input));
                  }
                  return Mono.just(input);
                })
                .flatMap(input -> {
                  log.info("third [{}]", input);
                  return Mono.just(input);
                })
                .flatMap(input -> {
                  log.info("fourth [{}]", input);
                  return Mono.just(input);
                })
                .onErrorResume(throwable -> {
                    log.error("something error catch ", throwable);
                    return Mono.just("error -1");
                })
                .blockLast();
    }

}
