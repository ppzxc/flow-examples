package org.example.r2dbc.domain.role;

import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.EnumWriteSupport;

@WritingConverter
public class RoleTypeWritingConverter extends EnumWriteSupport<RoleEntity.Type> {
}
