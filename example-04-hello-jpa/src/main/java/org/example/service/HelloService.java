package org.example.service;

import java.util.Optional;

public interface HelloService<T> {

  Optional<T> findById(Long id);

  T add(T target);
}
