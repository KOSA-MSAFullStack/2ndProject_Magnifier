package com.magnifier.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder {

    /**
     * 비밀번호를 인코딩하는 메서드
     * 여기서는 인코딩 없이 원문 그대로 문자열로 변환하여 반환함
     * @param rawPassword 원본 비밀번호
     * @return 인코딩된 비밀번호 (여기선 원본 그대로 반환)
     */
    @Override
    public String encode(CharSequence rawPassword) {
        log.warn("before encode :" + rawPassword);
        return rawPassword.toString();
    } // end encode

    /**
     * 비밀번호가 일치하는지 검사하는 메서드
     * 원본(rawPassword)과 저장된(encodedPassword) 비밀번호를 단순 문자열 비교
     * @param rawPassword 사용자 입력 비밀번호
     * @param encodedPassword 저장된 비밀번호 (원본 그대로임)
     * @return 두 비밀번호가 같으면 true, 아니면 false
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.warn("matches: " + rawPassword + ":" + encodedPassword);
        return rawPassword.toString().equals(encodedPassword);
    } // end matches
} // end class
