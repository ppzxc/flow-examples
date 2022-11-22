package org.example.r2dbc.domain.role;

import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class RoleReadingConverter implements Converter<Row, RoleEntity> {

    @Override
    public RoleEntity convert(Row source) {
        return RoleEntity.builder()
                .id(source.get("id", Long.class))
                .type(source.get("type", RoleEntity.Type.class))
                .build();
    }
}
