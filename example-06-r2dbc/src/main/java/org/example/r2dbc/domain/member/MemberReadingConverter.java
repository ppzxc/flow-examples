package org.example.r2dbc.domain.member;

import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class MemberReadingConverter implements Converter<Row, MemberEntity> {

    @Override
    public MemberEntity convert(Row source) {
        return MemberEntity.builder()
                .id(source.get("id", Long.class))
                .email(source.get("email", String.class))
                .password(source.get("password", String.class))
                .name(source.get("name", String.class))
                .authorizationId(source.get("authorization_id", Long.class))
                .companyId(source.get("company_id", Long.class))
                .build();
    }
}
