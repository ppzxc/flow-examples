package org.example.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.example.SingletonEntityManager;
import org.example.entity.Member;

public class MemberServiceImpl implements HelloService {

  private final SingletonEntityManager singletonEntityManager = SingletonEntityManager.getInstance();

  @Override
  public Optional<Member> findById(Long id) {
    EntityManager entityManager = singletonEntityManager.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = entityManager.getTransaction();

    tx.begin();
    try {
      Optional<Member> result = Optional.of(entityManager.find(Member.class, id));
      tx.commit();
      return result;
    } catch (Exception e) {
      tx.rollback();
    } finally {
      entityManager.close();
    }
    return Optional.empty();
  }

  @Override
  public Object add(Object target) {
    EntityManager entityManager = singletonEntityManager.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = entityManager.getTransaction();

    tx.begin();
    try {
      entityManager.persist(target);
      tx.commit();
      return target;
    } catch (Exception e) {
      tx.rollback();
    } finally {
      entityManager.close();
    }
    return null;
  }
}
