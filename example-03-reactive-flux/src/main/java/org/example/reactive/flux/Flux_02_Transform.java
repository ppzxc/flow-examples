package org.example.reactive.flux;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Flux_02_Transform {

  public static void main(String[] args) {
    log.info("1 from");
    Flux.from(randomStringPublisher())
        .log()
        .blockLast();

    log.info("2 map");
    Flux.from(randomStringPublisher())
        .map(target -> target.toUpperCase())
        .log()
        .blockLast();

    log.info("3 flatMap");
    Flux.from(randomStringPublisher())
        .flatMap(target -> Mono.just(target.toUpperCase()))
        .log()
        .blockLast();

    log.info("4 concatMap");
    Flux.from(randomStringPublisher())
        .concatMap(target -> Mono.just(target.toUpperCase()))
        .log()
        .blockLast();

    log.info("5 flatMapSequential");
    Flux.from(randomStringPublisher())
        .flatMapSequential(target -> Mono.just(target.toUpperCase()))
        .log()
        .blockLast();

    log.info("6 flatMap boundedElastic");
    Flux.from(randomStringPublisher())
        .flatMap(target -> Mono.just(target.toUpperCase()))
        .subscribeOn(Schedulers.boundedElastic())
        .log()
        .blockLast();

    log.info("7 flatMap parallel");
    Flux.from(randomStringPublisher())
        .flatMap(target -> Mono.just(target.toUpperCase()))
        .subscribeOn(Schedulers.parallel())
        .log()
        .blockLast();

    // 스케줄러 호출 후 바로 다음 플럭스 실행시 스레드가 패러랠로 실행됨
    // 아무래도 Schedulers 에서 할당된 쓰레드가 있어서 그러는거 같은데 왜이럼??????????????????????????????????????????
    log.info("8 NON PARALLEL");
    Flux.from(randomStringPublisher().delayElements(Duration.ofMillis(14)))
        .log()
        .blockLast();

    log.info("9 NON PARALLEL - MERGE = PARALLEL");
    Flux.merge(randomStringPublisher().delayElements(Duration.ofMillis(14)),
            randomStringPublisher().delayElements(Duration.ofMillis(33)),
            randomStringPublisher().delayElements(Duration.ofMillis(28)))
        .flatMap(target -> Mono.just(target.toUpperCase()))
        .log()
        .blockLast();

    log.info("10 PARALLEL");
    Flux.merge(randomStringPublisher().delayElements(Duration.ofMillis(14)),
            randomStringPublisher().delayElements(Duration.ofMillis(33)),
            randomStringPublisher().delayElements(Duration.ofMillis(28)))
        .flatMap(target -> Mono.just(target.toUpperCase()))
        .subscribeOn(Schedulers.parallel())
        .log()
        .blockLast();

    log.info("11 ?? WHY PARALLEL");
    Flux.from(randomStringPublisher())
        .log()
        .blockLast();
  }

  private static Flux<String> randomStringPublisher() {
    return Flux.range(1, 10).flatMap(unused -> Mono.just(Utils.randomString(10)));
  }


}
