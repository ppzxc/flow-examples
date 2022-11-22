package org.example.r2dbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(value = "member_role")
public class MemberRoleEntity {

    @Column("member_id")
    private Long memberId;

    @Column("role_id")
    private Long roleId;
}
