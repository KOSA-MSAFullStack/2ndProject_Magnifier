package com.magnifier.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;

/**
 * member에 insert하는 test
 * @author 김경아
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit4ClassRunner를 사용해 Spring 환경에서 테스트 실행
@ContextConfiguration({ // 테스트에 필요한 컨텍스트 설정 파일 경로 지정
  "file:src/main/webapp/WEB-INF/spring/root-context.xml",
  "file:src/main/webapp/WEB-INF/spring/security-context.xml"
  })
@Log4j
public class MemberTests {

  @Autowired
  private PasswordEncoder passwordEncoder; // 스프링 빈으로 등록된 PasswordEncoder 자동 주입 (BCryptPasswordEncoder 구현체)
 
  @Autowired
  private DataSource dataSource; // 데이터베이스 연결을 위한 DataSource 자동 주입
 
  @Test
  public void testInsertMember() {
	// member 테이블에 데이터 삽입
    String sql = "insert into zoomin.member"
    		+ "(member_id, login_id, name, gender, password, phone_number, birth, address) "
    		+ "values "
    		+ "(?,?,?,?,?,?,?,?)";
   
    // 임시 데이터
    String loginId = "asd";
    String name = "leee";
    String gender = "M";  
    String phone = "01000000000";  
    String birth = "19900101";       
    String address = "Daejeon";        
    
    Connection con = null;
    PreparedStatement pstmt = null;
	 
    try {
	    con = dataSource.getConnection();
	    pstmt = con.prepareStatement(sql);        
	    pstmt.setInt(1, 1);  
	    pstmt.setString(2, loginId);
	    pstmt.setString(3, name);
	    pstmt.setString(4, gender);
	    pstmt.setString(5, passwordEncoder.encode("asd"));
	    pstmt.setString(6, phone);
	    pstmt.setString(7, birth);
	    pstmt.setString(8, address);
	    
	    pstmt.executeUpdate(); // SQL 실행(데이터 삽입)      
    } catch(Exception e) {
    	// 예외 발생 시 출력
    	e.printStackTrace();
    } finally {
		// 리소스 정리: PreparedStatement, Connection 반환
	    if (pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
	    if (con != null) { try { con.close();  } catch(Exception e) {} }        
    }//end try

  }//void testInsertMember()  

}//end class
