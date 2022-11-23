package org.example.reactive.flux;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FluxPractice04 {

  public static void main(String[] args) {
    Flux<Integer> firstPublisher = Flux.range(1, 100)
        .delayElements(Duration.ofMillis(17));

    Flux<Integer> secondPublisher = Flux.range(1, 100)
        .delayElements(Duration.ofMillis(38));

    Flux<Integer> thirdPublisher = Flux.range(1, 100)
        .delayElements(Duration.ofMillis(54));

    Flux.merge(firstPublisher, secondPublisher, thirdPublisher)
        .takeLast(10)
        .doOnRequest(request -> log.info("request {}", request))
        .doOnNext(integer -> log.info("{}", integer))
        .log()
        .blockLast();
  }

}
