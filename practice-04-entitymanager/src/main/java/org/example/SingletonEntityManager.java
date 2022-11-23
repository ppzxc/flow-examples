package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class SingletonEntityManager {

  private final EntityManagerFactory entityManagerFactory;

  private SingletonEntityManager() {
    this.entityManagerFactory = Persistence.createEntityManagerFactory("hello");
  }

  public EntityManagerFactory getEntityManagerFactory() {
    return entityManagerFactory;
  }

  public static SingletonEntityManager getInstance() {
    return Singleton.INSTANCE;
  }

  private static final class Singleton {

    private static final SingletonEntityManager INSTANCE = new SingletonEntityManager();
  }
}
