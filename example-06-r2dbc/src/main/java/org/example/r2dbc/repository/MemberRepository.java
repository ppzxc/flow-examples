package org.example.r2dbc.repository;

import kr.nanoit.mo.domain.member.MemberEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MemberRepository extends R2dbcRepository<MemberEntity, Long> {
    Mono<Boolean> existsByEmail(String email);
}