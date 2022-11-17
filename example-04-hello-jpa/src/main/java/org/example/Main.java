package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.example.entity.Member;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction tx = entityManager.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setId(1L);
      member.setName("조정하");
      entityManager.persist(member);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      entityManager.close();
    }

    entityManagerFactory.close();
  }
}