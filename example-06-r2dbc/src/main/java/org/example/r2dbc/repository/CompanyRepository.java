package org.example.r2dbc.repository;

import kr.nanoit.mo.domain.CompanyEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends R2dbcRepository<CompanyEntity, Long> {

}