package com.example.jwt.service;

import com.example.jwt.model.dto.MemberDto;
import com.example.jwt.model.dto.SignUpDto;
import com.example.jwt.model.entity.JwtToken;

public interface MemberService {
    JwtToken signIn(String username, String password);

    MemberDto signUp(SignUpDto signUpDto);
}
