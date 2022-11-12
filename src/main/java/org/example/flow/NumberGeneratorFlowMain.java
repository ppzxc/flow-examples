package org.example.flow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberGeneratorFlowMain {

  public static void main(String[] args) {
    log.info("main: create number subscriber");
    NumberSubscriber numberSubscriber = new NumberSubscriber();
    log.info("main: create number generator publisher");
    NumberGeneratorPublisher numberGeneratorPublisher = new NumberGeneratorPublisher(25);
    log.info("main: publisher subscribe numberSubscriber");
    numberGeneratorPublisher.subscribe(numberSubscriber);

    log.info("main: call request 10");
    numberSubscriber.request(10);
    log.info("main: call request 10");
    numberSubscriber.request(10);
    log.info("main: call request 10");
    numberSubscriber.request(10);
  }
}