package com.magnifier.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.magnifier.member.entity.Member;
import com.magnifier.member.mapper.MemberMapper;
import com.magnifier.security.domain.CustomMember;

import lombok.extern.log4j.Log4j;

/**
 * 로그인 시 해당 개인 회원의 정보를 조회하여 UserDetails 객체 형태로 반환
 * @author 김경아
 *
 */
@Log4j
public class CustomUserDetailsServiceMember implements UserDetailsService {
   
    @Autowired
    private MemberMapper mapper; // DB에서 회원 정보를 조회하는 매퍼
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        log.warn("Load User By UserName :" + username);      
        // 회원 아이디(loginId)를 받아 DB에서 회원 정보(MemberDto)를 조회
        Member member = mapper.read(username);     
        log.warn("Query by member mapper :" + member);
        
        return new CustomMember(member);
    }
}