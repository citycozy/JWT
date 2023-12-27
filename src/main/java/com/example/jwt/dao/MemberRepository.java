package com.example.jwt.dao;

import com.example.jwt.domain.dto.SignUpDto;
import com.example.jwt.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String memberId);

    boolean existsByUsername(String username);
}
