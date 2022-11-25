package org.example.reactive.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class PracticeMono01 {
    public static void main(String[] args) {
        Mono.just(1)
                .flatMap(data -> {
                    log.info("something async processing 1");
                    return Mono.just(data + " first");
                })
                .flatMap(data -> {
                    log.info("something async processing 2");
                    return Mono.just(data + " second");
                })
                .flatMap(data -> {
                    log.info("something async processing 3");
                    return Mono.error(new IllegalArgumentException("something exception"));
//                    return Mono.just(data + " third");
                })
//                .onErrorResume(throwable -> {
//                    log.error("something throwing ", throwable);
//                    return Mono.just(" exception");
//                })
                .flatMap(data -> {
                    log.info("something async processing 4");
                    return Mono.just(data + " fourth");
                })
                .flatMap(data -> {
                    log.info("something async processing 5");
                    return Mono.just(data + " fifth");
                })
                .onErrorResume(throwable -> {
                    log.error("something throwing ", throwable);
                    return Mono.just(" exception");
                })
                .doOnNext(data -> log.info("complete [{}]", data))
                .block();
    }
}
