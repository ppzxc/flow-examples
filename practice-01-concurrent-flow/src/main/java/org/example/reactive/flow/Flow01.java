package org.example.reactive.flow;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Flow01 {

  public void main(String[] args) {
    log.info("main: create number subscriber");
    NumberSubscriber numberSubscriber = new NumberSubscriber();
    log.info("main: create number generator publisher");
    NumberPublisher numberPublisher = new NumberPublisher(25);
    log.info("main: publisher subscribe numberSubscriber");
    numberPublisher.subscribe(numberSubscriber);

    log.info("main: call request 10");
    numberSubscriber.request(10);
    log.info("main: call request 10");
    numberSubscriber.request(10);
    log.info("main: call request 10");
    numberSubscriber.request(10);
  }

  public class NumberSubscriber implements Subscriber<Long> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
      log.info("NumberSubscriber: onSubscribe");
      this.subscription = subscription;
    }

    @Override
    public void onNext(Long item) {
      log.info("NumberSubscriber: onNext {}", item);
    }

    @Override
    public void onError(Throwable throwable) {
      log.error("NumberSubscriber: onError", throwable);
    }

    @Override
    public void onComplete() {
      log.info("NumberSubscriber: onComplete");
    }

    public void request(long n) {
      subscription.request(n);
    }
  }


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
}