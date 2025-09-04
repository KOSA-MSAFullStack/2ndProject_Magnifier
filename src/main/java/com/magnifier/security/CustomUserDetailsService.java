package com.magnifier.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.magnifier.member.dto.MemberDto;
import com.magnifier.member.mapper.MemberMapper;
import com.magnifier.security.domain.CustomMember;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
   
    @Autowired
    private MemberMapper membermapper; // DB에서 회원 정보를 조회하는 매퍼
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        log.warn("Load User By UserName :" + username);
        log.warn("membermapper is null? : " + (membermapper == null));
       
        // 회원 아이디(username)를 받아 DB에서 회원 정보(MemberDto)를 조회
        MemberDto dto = membermapper.read(username);     
        log.warn("MemberDto is null? : " + (dto == null));
        log.warn("Query by member mapper :" + dto);
        log.warn("dto.getLoginId(): " + dto.getLoginId());
        log.warn("dto.getPassword(): " + dto.getPassword());
        
        return new CustomMember(dto);
    }
}