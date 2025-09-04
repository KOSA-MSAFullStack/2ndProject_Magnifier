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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
  "file:src/main/webapp/WEB-INF/spring/root-context.xml",
  "file:src/main/webapp/WEB-INF/spring/security-context.xml"
  })
@Log4j
public class MemberTests {

  @Autowired
  private PasswordEncoder passwordEncoder;
 
  @Autowired
  private DataSource dataSource;
 
  @Test
  public void testInsertMember() {

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
	    
	    pstmt.executeUpdate();        
	  }catch(Exception e) {
	    e.printStackTrace();
	  }finally {
	    if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
	    if(con != null) { try { con.close();  } catch(Exception e) {} }        
	  }//end try

  }//void testInsertMember()  
 
 
}//end class
