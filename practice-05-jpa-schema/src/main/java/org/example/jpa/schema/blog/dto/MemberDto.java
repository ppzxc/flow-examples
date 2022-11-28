package org.example.jpa.schema.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link org.example.jpa.schema.blog.entity.MemberEntity} entity
 */
@Data
public class MemberDto implements Serializable {
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
    private final Long id;
    private final String username;
    private final String password;
    private final String email;
}