package com.example.jwt.domain.dto;

import com.example.jwt.domain.entity.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRoles())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }

}
