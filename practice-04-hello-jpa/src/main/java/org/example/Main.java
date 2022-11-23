package org.example;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.example.entity.Member;
import org.example.entity.Team;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction tx = entityManager.getTransaction();
    tx.begin();

    try {
      Team team = new Team();
      team.setName("new team");
      entityManager.persist(team);

      Member member = new Member();
      member.setName("조정하2");
      entityManager.persist(member);

      team.setMembers(Set.of(member));
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      entityManager.close();
    }

    entityManagerFactory.close();
  }
}