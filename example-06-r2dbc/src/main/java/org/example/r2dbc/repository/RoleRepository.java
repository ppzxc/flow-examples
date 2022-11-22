package org.example.r2dbc.repository;

import kr.nanoit.mo.domain.role.RoleEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends R2dbcRepository<RoleEntity, Long> {

}