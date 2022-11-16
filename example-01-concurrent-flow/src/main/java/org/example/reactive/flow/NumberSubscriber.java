package org.example.reactive.flow;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
