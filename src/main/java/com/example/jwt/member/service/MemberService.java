package com.example.jwt.member.service;

import com.example.jwt.member.domain.dto.MemberDto;
import com.example.jwt.member.domain.dto.SignUpDto;
import com.example.jwt.member.domain.entity.JwtToken;

public interface MemberService {
    JwtToken signIn(String username, String password);
    MemberDto signUp(SignUpDto signUpDto);
}
