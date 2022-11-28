package org.example.jpa.schema.blog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa.schema.blog.dto.RegisterDto;
import org.example.jpa.schema.blog.entity.MemberEntity;
import org.example.jpa.schema.blog.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Optional<MemberEntity> register(RegisterDto registerDto) {
        return Optional.empty();
    }
}
