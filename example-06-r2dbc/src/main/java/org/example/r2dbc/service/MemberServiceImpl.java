package org.example.r2dbc.service;

import kr.nanoit.mo.domain.AuthorizationEntity;
import kr.nanoit.mo.domain.MemberRoleEntity;
import kr.nanoit.mo.domain.member.MemberEntity;
import kr.nanoit.mo.domain.request.RequestSignUpDto;
import kr.nanoit.mo.domain.response.ResponseSignUp;
import kr.nanoit.mo.exception.http.BadRequestException;
import kr.nanoit.mo.extension.ErrorCode;
import kr.nanoit.mo.repository.AuthorizationRepository;
import kr.nanoit.mo.repository.MemberRepository;
import kr.nanoit.mo.repository.MemberRoleRepository;
import kr.nanoit.mo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AuthorizationRepository authorizationRepository;
    private final RoleRepository roleRepository;
    private final MemberRoleRepository memberRoleRepository;

    @Transactional
    @Override
    public Mono<ResponseSignUp> signUp(RequestSignUpDto requestSignUpDto) {
        return memberRepository.existsByEmail(requestSignUpDto.getEmail())
                .onErrorMap(throwable -> new BadRequestException(ErrorCode.SIGN_UP_DUPLICATION, "duplicate email"))
                .flatMap(exist -> {
                    if (exist) {
                        return Mono.error(new BadRequestException(ErrorCode.SIGN_UP_DUPLICATION, "duplicate email"));
                    } else {
                        return authorizationRepository.save(new AuthorizationEntity()
                                .setAccountNonExpired(true)
                                .setAccountNonLocked(true)
                                .setCredentialsNonExpired(true)
                                .setEnabled(true));
                    }
                }).flatMap(authorization -> memberRepository.save(MemberEntity.builder()
                        .email(requestSignUpDto.getEmail())
                        .password(requestSignUpDto.getPassword())
                        .name(requestSignUpDto.getName())
                        .authorizationId(authorization.getId())
                        .build()))
                .flatMap(memberEntity -> roleRepository.findAll()
                        .flatMap(roleEntity -> memberRoleRepository.save(new MemberRoleEntity()
                                .setMemberId(memberEntity.getId())
                                .setRoleId(roleEntity.getId())))
                        .then(Mono.just(new ResponseSignUp()
                                .setEmail(memberEntity.getEmail())
                                .setName(memberEntity.getName()))));
    }
}
