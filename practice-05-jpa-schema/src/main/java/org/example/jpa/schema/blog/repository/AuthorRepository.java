package org.example.jpa.schema.blog.repository;

import org.example.jpa.schema.blog.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>, JpaSpecificationExecutor<AuthorEntity> {
}