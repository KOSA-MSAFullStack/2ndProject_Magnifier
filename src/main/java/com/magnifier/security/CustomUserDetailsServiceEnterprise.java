package com.magnifier.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.magnifier.enterprise.entity.Enterprise;
import com.magnifier.enterprise.mapper.EnterpriseMapper;
import com.magnifier.security.domain.CustomEnterprise;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsServiceEnterprise implements UserDetailsService {
   
    @Autowired
    private EnterpriseMapper mapper; // DB에서 회원 정보를 조회하는 매퍼
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        log.warn("Load User By UserName :" + username);      
        // 회원 아이디(registerNumber)를 받아 DB에서 기업 정보(EnterpriseDto)를 조회
        Enterprise enterprise = mapper.read(username);     
        log.warn("Query by member mapper :" + enterprise);
        
        return new CustomEnterprise(enterprise);
    }
}