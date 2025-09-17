package com.magnifier.security.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.magnifier.enterprise.entity.Enterprise;

import lombok.Getter;

/**
 * 기업 회원 인증 과정에서 사용자 정보를 더 풍부하게 담아 관리
 * @author 김경아
 *
 */
@Getter
public class CustomEnterprise extends User {

    // 회원 도메인 정보를 담는 필드
    private int enterpriseId; // enterpriseId만 저장(최소한의 정보)

    // User 클래스로 직접 생성할 때 사용하는 생성자(필요에 따라 사용 가능)
    public CustomEnterprise(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    // Enterprise를 받아 User 클래스에 필요한 값으로 변환 후 생성
    public CustomEnterprise(Enterprise enterprise) {
        super(
    		enterprise.getRegisterNumber(),  // registerNumber: 로그인 아이디
    		enterprise.getPassword(), // password: 비밀번호

            // auth 필드가 null이 아니면, 권한을 SimpleGrantedAuthority로 감싸서 리스트로 만들어 넘김
    		enterprise.getAuth() != null
                ? java.util.Collections.singletonList(new SimpleGrantedAuthority(enterprise.getAuth()))
                : java.util.Collections.emptyList()  // auth가 없으면 빈 리스트
        );
        this.enterpriseId = enterprise.getEnterpriseId();  // 도메인 객체 참조 저장
    }
}
