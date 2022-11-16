package org.example.reactive.flow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) {
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
}