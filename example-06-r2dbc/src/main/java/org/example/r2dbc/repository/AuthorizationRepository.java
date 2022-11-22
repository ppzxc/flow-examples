package org.example.r2dbc.repository;

import kr.nanoit.mo.domain.AuthorizationEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends R2dbcRepository<AuthorizationEntity, Long> {

}