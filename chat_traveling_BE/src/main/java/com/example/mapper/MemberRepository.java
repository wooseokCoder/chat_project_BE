package com.example.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
