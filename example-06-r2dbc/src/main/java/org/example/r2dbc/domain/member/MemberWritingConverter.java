package org.example.r2dbc.domain.member;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class MemberWritingConverter implements Converter<MemberEntity, OutboundRow> {

    @Override
    public OutboundRow convert(MemberEntity source) {
        OutboundRow outboundRow = new OutboundRow();
        outboundRow.put("id", Parameter.from(source.getId()));
        outboundRow.put("email", Parameter.from(source.getEmail()));
        outboundRow.put("password", Parameter.from(source.getPassword()));
        outboundRow.put("name", Parameter.from(source.getName()));
        outboundRow.put("authorization_id", Parameter.from(source.getAuthorizationId()));
        outboundRow.put("company_id", Parameter.from(source.getCompanyId()));
        return outboundRow;
    }
}
