package org.example.jpa.schema.blog.repository;

import org.example.jpa.schema.blog.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long>, JpaSpecificationExecutor<HashTagEntity> {
}