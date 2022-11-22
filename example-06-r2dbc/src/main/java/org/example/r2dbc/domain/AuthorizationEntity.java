package org.example.r2dbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "member_authorization")
public class AuthorizationEntity {
    @Id
    @Column("id")
    private Long id;

    @Column("account_non_expired")
    private boolean accountNonExpired;

    @Column("account_non_locked")
    private boolean accountNonLocked;

    @Column("credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column("enabled")
    private boolean enabled;
}
