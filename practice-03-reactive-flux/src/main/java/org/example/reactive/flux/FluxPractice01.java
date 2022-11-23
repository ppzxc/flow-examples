package org.example.reactive.flux;

import java.time.Duration;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class FluxPractice01 {

  public static void main(String[] args) {
    fluxFromIntStream();
    fluxMapToLong();
    fluxWrappingFluxError();
    fluxThrowErrorWithResume();
    fluxThrowErrorWithReturn();
    fluxThrowErrorWithContinue();
    fluxThrowErrorWithComplete();
    fluxThrowErrorWithMap();
    fluxFlatMapWithTake();
    fluxFlatMapWithTake2();
    fluxDelayPublisher();
  }

  public static void fluxFromIntStream() {
    log.info("fluxFromIntStream --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .doOnNext(value -> log.info("doOnNext {}", value))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxMapToLong() {
    log.info("fluxMapToLong --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .map(Integer::longValue)
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxWrappingFluxError() {
    log.info("fluxWrappingFluxError --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .map(integer -> {
          if (integer == 5) {
            return Flux.error(new RuntimeException());
          } else {
            return integer.longValue();
          }
        })
        .onErrorResume(throwable -> Mono.just(5))
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxThrowErrorWithResume() {
    log.info("fluxThrowErrorWithResume --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .map(integer -> {
          if (integer == 5) {
            throw new RuntimeException();
          } else {
            return integer.longValue();
          }
        })
        .onErrorResume(throwable -> Mono.just(5L))
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxThrowErrorWithReturn() {
    log.info("fluxThrowErrorWithReturn --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .map(integer -> {
          if (integer == 5) {
            throw new RuntimeException();
          } else {
            return integer.longValue();
          }
        })
        .onErrorReturn(throwable -> throwable instanceof RuntimeException, 5L)
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxThrowErrorWithContinue() {
    log.info("fluxThrowErrorWithContinue --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .map(integer -> {
          if (integer == 5) {
            throw new RuntimeException();
          } else {
            return integer.longValue();
          }
        })
        .onErrorContinue((throwable, o) -> {
          log.warn("onErrorContinue {} {}", o, throwable.getMessage());
        })
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxThrowErrorWithComplete() {
    log.info("fluxThrowErrorWithComplete --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .map(integer -> {
          if (integer == 5) {
            throw new RuntimeException();
          } else {
            return integer.longValue();
          }
        })
        .onErrorComplete(throwable -> throwable instanceof RuntimeException)
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxThrowErrorWithMap() {
    try {
      log.info("fluxThrowErrorWithMap --------------");
      Flux.fromStream(IntStream.range(1, 10).boxed())
          .log()
          .map(integer -> {
            if (integer == 5) {
              throw new RuntimeException();
            } else {
              return integer.longValue();
            }
          })
          .onErrorMap(throwable -> new IllegalArgumentException())
          .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
          .doOnError(throwable -> log.error("doOnError", throwable))
          .blockLast();
    } catch (Exception e) {

    }
  }

  public static void fluxFlatMapWithTake() {
    log.info("fluxFlatMapWithTake --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .flatMap(integer -> Mono.just(integer.longValue()))
        .take(5)
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxFlatMapWithTake2() {
    log.info("fluxFlatMapWithTake2 --------------");
    Flux.fromStream(IntStream.range(1, 10).boxed())
        .log()
        .flatMap(integer -> Mono.just(integer.longValue()))
        .delayElements(Duration.ofSeconds(1))
        .take(Duration.ofSeconds(5))
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }

  public static void fluxDelayPublisher() {
    log.info("fluxDelayPublisher --------------");
    Flux.from(Flux.fromStream(IntStream.range(1, 10).boxed()).delayElements(Duration.ofSeconds(1)))
        .log()
        .flatMap(integer -> Mono.just(integer.longValue()))
        .doOnNext(value -> log.info("doOnNext {} {}", value, value.getClass().getName()))
        .doOnError(throwable -> log.error("doOnError", throwable))
        .blockLast();
  }
}
