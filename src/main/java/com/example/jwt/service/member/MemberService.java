package com.example.jwt.service.member;

import com.example.jwt.domain.dto.MemberDto;
import com.example.jwt.domain.dto.SignUpDto;
import com.example.jwt.domain.entity.JwtToken;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {
    JwtToken signIn(String username, String password);

    MemberDto signUp(SignUpDto signUpDto);
}
