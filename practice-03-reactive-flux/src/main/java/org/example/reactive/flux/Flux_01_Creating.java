package org.example.reactive.flux;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SynchronousSink;

public class Flux_01_Creating {

  public static void main(String[] args) {
    Flux.just(1) // NOT NULL
        .log()
        .blockLast();

    Flux.fromArray(IntStream.range(1, 10).boxed().toArray()) // NOT NULL
        .log()
        .blockLast();

    Flux.fromIterable(IntStream.range(1, 10).boxed().toList())
        .log()
        .blockLast();

    Flux.range(1, 10)
        .log()
        .blockLast();

    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .blockLast();

    Flux.fromStream(Stream::empty)
        .log()
        .blockLast();

    // 즉시 종료
    Flux.empty()
        .log()
        .blockLast();

    try {
      Flux.error(new IllegalArgumentException())
          .log()
          .blockLast();
    } catch (IllegalArgumentException e) {
      System.err.println(e);
    }

    // empty 는 즉시 종료 되지만 아무것도 하지 않는 never 는 값 방출이 없으니 계속 블락됨
    // 어디에 쓰지?
//    Flux.never()
//        .log()
//        .blockLast();

    // 구독시 퍼블리셔를 결정
    Flux.defer(() -> Flux.range(1, 10))
        .log()
        .blockLast();

    // ?? 용처를 이해할 수 없음
//    Flux.deferContextual();

    // ?? 용처를 이해할 수 없음
//    Flux.using(() -> IntStream.range(1, 10).boxed().toList())
//        .log()
//        .blockLast();

    // sync flux
    // SYNC 지만 방출 리스트를 FLUX로 작성
    // ?? 동기 및 하나씩 방출된다는데 하나만 받고 에러가 발생함??
    try {
      Flux.generate(synchronousSink -> Flux.range(1, 10)
              .delayElements(Duration.ofMillis(100))
              .log()
              .doOnNext(synchronousSink::next)
              .doOnError(synchronousSink::error)
              .doOnComplete(synchronousSink::complete)
              .blockLast())
          .log()
          .doOnNext(System.out::println)
          .blockLast();
    } catch (Throwable e) {
    }

    Flux.create(fluxSink -> Flux.range(1, 10)
            .delayElements(Duration.ofMillis(100))
            .log()
            .doOnNext(fluxSink::next)
            .doOnError(fluxSink::error)
            .doOnComplete(fluxSink::complete)
            .blockLast())
        .log()
        .blockLast();
  }
}
