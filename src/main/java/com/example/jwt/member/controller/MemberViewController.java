package com.example.jwt.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberViewController {

    @GetMapping("sign-in")
    public String signIn() {
        return "member/sign-in-form";
    }

    @GetMapping("sign-up")
    public String signUp() {
        return "member/sign-up-form";
    }


}
