package org.example.jpa.schema.blog.service;

import org.example.jpa.schema.blog.entity.MemberEntity;

import java.util.Optional;

public interface MemberService {
    Optional<MemberEntity> register();
}
