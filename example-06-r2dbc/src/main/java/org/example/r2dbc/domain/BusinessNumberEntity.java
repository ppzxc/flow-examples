package org.example.r2dbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "business_number")
public class BusinessNumberEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("number")
    private String number;

    @Column("country_code")
    private String country_code;

    @Column("company_id")
    private Long companyId;
}
