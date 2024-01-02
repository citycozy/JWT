package com.example.jwt.member.service;


import com.example.jwt.member.repository.MemberRepository;
import com.example.jwt.member.domain.dto.MemberDto;
import com.example.jwt.member.domain.dto.SignUpDto;
import com.example.jwt.member.domain.entity.JwtToken;
import com.example.jwt.member.domain.entity.Member;
import com.example.jwt.member.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public JwtToken signIn(String username, String password) {
        Member member = memberRepository.getByUsername(username);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, member.getPassword());

        // 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보를 기반으로 JWT 토큰 생성 후 리턴
        return jwtTokenProvider.generateToken(authentication);
    }

    @Transactional
    @Override
    public MemberDto signUp(SignUpDto signUpDto) {
        if (memberRepository.existsByUsername(signUpDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용중인 아이디 입니다.");
        }

        // password 암호화
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER");

        return MemberDto.toDto(memberRepository.save(signUpDto.toEntity(encodedPassword, roles)));
    }
}
