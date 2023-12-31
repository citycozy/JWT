package com.example.jwt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {

    // JWT 에 대한 인증 타입
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
