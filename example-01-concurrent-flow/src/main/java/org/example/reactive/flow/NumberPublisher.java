package org.example.reactive.flow;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberPublisher implements Publisher<Long> {

  private final Long maxValue;
  private final AtomicLong numberGenerator;

  public NumberPublisher(long maxValue) {
    this.maxValue = maxValue;
    this.numberGenerator = new AtomicLong(0);
  }

  @Override
  public void subscribe(Subscriber<? super Long> subscriber) {
    log.info("publisher: subscribe");

    Subscription subscription = new Subscription() {
      @Override
      public void request(long n) {
        log.info("subscription: request {}", n);
        for (long i = 0; i < n; i++) {
          long andIncrement = numberGenerator.incrementAndGet();
          if (andIncrement == maxValue) {
            log.info("subscription: call onComplete");
            subscriber.onComplete();
            return;
          } else {
            log.info("subscription: call onNext");
            subscriber.onNext(andIncrement);
          }
        }
      }

      @Override
      public void cancel() {
        log.info("subscription: cancel");
        subscriber.onComplete();
      }
    };

    subscriber.onSubscribe(subscription);
  }
}
