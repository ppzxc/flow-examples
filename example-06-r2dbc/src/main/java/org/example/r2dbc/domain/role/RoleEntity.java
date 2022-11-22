package org.example.r2dbc.domain.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "role")
public class RoleEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("type")
    private Type type;

    public enum Type {
        ROLE_ADMIN,
        ROLE_COMPANY,
        ROLE_USER
    }
}
