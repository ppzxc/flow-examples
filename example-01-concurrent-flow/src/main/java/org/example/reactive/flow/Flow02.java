package org.example.reactive.flow;

import java.time.Duration;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Flow02 {

  public static void main(String[] args) {
    LimitSubscriber limitSubscriber = new LimitSubscriber();
    LimitPublisher limitPublisher = new LimitPublisher(100, Duration.ofMillis(100));
    limitPublisher.subscribe(limitSubscriber);

    limitSubscriber.start();
  }

  @RequiredArgsConstructor
  public static class LimitPublisher implements Publisher<Integer> {

    private final int limit;
    private final Duration delay;
    private final AtomicInteger virtual = new AtomicInteger(0);

    private Integer virtualGenerator() {
      return virtual.incrementAndGet();
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
      subscriber.onSubscribe(new Subscription() {
        @Override
        public void request(long n) {
          for (int i = 0; i < n; i++) {
            Integer item = virtualGenerator();
            if (item >= limit) {
              subscriber.onComplete();
              return;
            }
            subscriber.onNext(item);
          }
        }

        @Override
        public void cancel() {
          log.info("cancel {}", virtual.get());
        }
      });
    }
  }

  public static class LimitSubscriber implements Subscriber<Integer> {

    private final AtomicBoolean flag = new AtomicBoolean(true);
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
      this.subscription = subscription;
    }

    @Override
    public void onNext(Integer item) {
      log.info("onNext {}", item);
    }

    @Override
    public void onError(Throwable throwable) {
      log.error("onError", throwable);
      flag.set(false);
    }

    @Override
    public void onComplete() {
      log.info("onComplete");
      flag.set(false);
    }

    public void start() {
      while (flag.get()) {
        log.info("called request 10");
        subscription.request(10);
      }
      log.info("subscribe close");
    }
  }
}
