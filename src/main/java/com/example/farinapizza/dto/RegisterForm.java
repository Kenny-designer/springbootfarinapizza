package com.example.farinapizza.dto;

import com.example.farinapizza.entity.Member;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
// 註冊表單轉換成@Entity物件，且保持僅單一功能-封裝、驗證長度、格式，不負責商業邏輯的處理
public class RegisterForm {
    // 此處的屬性只包含前端傳遞來的各個欄位，不包含其餘後端資料庫的欄位
    private String username;
    private String email;
    private String password;

    // 此轉換器只執行單純的 @Entity 的 DTO
    public Member toEntity(String encodedPassword, short verificationCode) {
        Member member = new Member();
        member.setUsername(username);
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setRegistered(verificationCode);
        return member;
    }
}
