package com.example.jwt.member.controller;

import com.example.jwt.member.domain.dto.MemberDto;
import com.example.jwt.member.domain.dto.SignInDto;
import com.example.jwt.member.domain.dto.SignUpDto;
import com.example.jwt.member.domain.entity.JwtToken;
import com.example.jwt.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sign")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/up")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto) {
        MemberDto savedMemberDto = memberService.signUp(signUpDto);

        return ResponseEntity.ok(savedMemberDto);
    }

    @PostMapping("/in")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();

        return memberService.signIn(username, password);
    }

}

