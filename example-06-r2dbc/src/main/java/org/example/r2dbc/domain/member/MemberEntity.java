package org.example.r2dbc.domain.member;

import java.time.OffsetDateTime;
import java.util.Set;
import kr.nanoit.mo.domain.AuthorizationEntity;
import kr.nanoit.mo.domain.CompanyEntity;
import kr.nanoit.mo.domain.role.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "member")
public class MemberEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("name")
    private String name;

    @Column("authorization_id")
    private Long authorizationId;

    @Column("company_id")
    private Long companyId;

    @Column("created_at")
    private OffsetDateTime createdAt;

    @Column("last_modified_at")
    private OffsetDateTime lastModifiedAt;

    @Transient
    private AuthorizationEntity authorizationEntity;

    @Transient
    private CompanyEntity companyEntity;

    @Transient
    private Set<RoleEntity> roles;
}
