package org.example.r2dbc.repository;

import kr.nanoit.mo.domain.MemberRoleEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRoleRepository extends R2dbcRepository<MemberRoleEntity, Long> {

}