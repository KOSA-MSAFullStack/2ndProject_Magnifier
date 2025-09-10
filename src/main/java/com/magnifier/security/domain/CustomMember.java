package com.magnifier.security.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.magnifier.member.entity.Member;

import lombok.Getter;

@Getter
public class CustomMember extends User {
    // 회원 도메인 정보를 담는 필드
    private int memberId;   // memberId만 저장(최소한의 정보)

    // User 클래스로 직접 생성할 때 사용하는 생성자(필요에 따라 사용 가능)
    public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    // Member를 받아 User클래스에 필요한 값으로 변환 후 생성
    public CustomMember(Member member) {
        super(
    		member.getLoginId(),  // loginId: 로그인 아이디
    		member.getPassword(), // password: 비밀번호

            // auth 필드가 null이 아니면, 권한을 SimpleGrantedAuthority로 감싸서 리스트로 만들어 넘김
    		member.getAuth() != null
                ? java.util.Collections.singletonList(new SimpleGrantedAuthority(member.getAuth()))
                : java.util.Collections.emptyList()  // auth가 없으면 빈 리스트
        );
        this.memberId = member.getMemberId();  // 도메인 객체 참조 저장
    }
}