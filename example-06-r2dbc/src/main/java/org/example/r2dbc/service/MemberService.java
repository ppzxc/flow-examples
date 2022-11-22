package org.example.r2dbc.service;

import kr.nanoit.mo.domain.request.RequestSignUpDto;
import kr.nanoit.mo.domain.response.ResponseSignUp;
import reactor.core.publisher.Mono;

public interface MemberService {
    Mono<ResponseSignUp> signUp(RequestSignUpDto requestSignUpDto);
}
