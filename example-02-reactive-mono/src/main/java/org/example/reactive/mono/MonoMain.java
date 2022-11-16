package org.example.reactive.mono;

import java.time.Duration;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoMain {

  public static void main(String[] args) {
    monoCreateSuccess();
    monoCreateDispose();
    monoDefer();
    monoDelay();
  }

  public static void monoCreateSuccess() {
    log.info("---------- 1");
    Mono.create(monoSink -> {
          Thread someThread = new Thread(() -> {
            log.info("SOME THREAD START");
            // DO SOMETHING
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException e) {
              monoSink.error(e);
            }
            monoSink.success("RESULT");
            log.info("SOME THREAD LAST");
          });

          someThread.start();
          monoSink.onDispose(someThread::interrupt);
        }).log()
        .block();
  }

  public static void monoCreateDispose() {
    log.info("---------- 2");
    Mono.create(monoSink -> {
          Thread someThread = new Thread(() -> {
            log.info("SOME THREAD START");
            // DO SOMETHING
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException e) {
              monoSink.error(e);
              log.error("", e);
              return;
            }
            monoSink.success("RESULT");
            log.info("SOME THREAD LAST");
          });

          someThread.start();
          monoSink.onCancel(() -> {
            log.info("onCancel");
            someThread.interrupt();
          });

          monoSink.onDispose(() -> {
            log.info("onDispose");
            someThread.interrupt();
          });
        }).log()
        .doOnError(throwable -> log.error("", throwable))
        .block();
  }

  public static void monoDefer() {
    log.info("---------- 3");
    Mono.defer(() -> Mono.just(1))
        .log()
        .doOnNext(integer -> log.info("{}", integer))
        .doOnError(throwable -> log.error("", throwable))
        .block();
  }

  public static void monoDelay() {
    log.info("---------- 4");
    Mono.delay(Duration.ofSeconds(1))
        .log()
        .doOnNext(aLong -> log.info("{}", aLong))
        .doOnError(throwable -> log.error("", throwable))
        .block();
  }

  public static void mono4() {
    Mono.just(IntStream.range(1, 10).toArray())
        .log()
        .doOnNext(array -> log.info("{}", array))
        .block();
  }
}