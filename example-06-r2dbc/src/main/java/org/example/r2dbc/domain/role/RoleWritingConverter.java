package org.example.r2dbc.domain.role;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class RoleWritingConverter implements Converter<RoleEntity, OutboundRow> {

    @Override
    public OutboundRow convert(RoleEntity source) {
        OutboundRow outboundRow = new OutboundRow();
        outboundRow.put("id", Parameter.from(source.getId()));
        outboundRow.put("type", Parameter.from(source.getType()));
        return outboundRow;
    }
}
