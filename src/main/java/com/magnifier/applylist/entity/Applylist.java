// Applylist.java
// 지원 목록 엔티티

package com.magnifier.applylist.entity;

import java.util.Date;
import lombok.Data;

// * author: 김기성 
@Data
public class Applylist {
    private int applylistId; // 지원 목록 ID
    private int memberId;    // 회원 ID
    private int recruitId;   // 채용 공고 ID
    private Date createdAt;  // 지원 날짜
}
