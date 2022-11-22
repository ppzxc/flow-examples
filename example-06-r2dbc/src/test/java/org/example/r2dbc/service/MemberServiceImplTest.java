package org.example.r2dbc.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import kr.nanoit.mo.domain.member.MemberEntity;
import kr.nanoit.mo.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceImplTest extends AbstractExternalResourcesContainers {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll().block();
    }

    @Test
    void t1() {
        MemberEntity expected = memberRepository.save(MemberEntity.builder()
                .email("test@nanoit.kr")
                .password("asdf")
                .name("asdfasdf")
                .createdAt(OffsetDateTime.now())
                .lastModifiedAt(OffsetDateTime.now())
                .build()).block();

        MemberEntity actual = memberRepository.findById(expected.getId()).block();

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("createdAt", "lastModifiedAt")
                .isEqualTo(expected);
    }

    @Test
    void t2() {
        System.out.println(memberRepository.count().block());
    }
}