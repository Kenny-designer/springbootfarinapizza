package com.example.farinapizza.dto;

import com.example.farinapizza.entity.Member;
import com.example.farinapizza.util.GenerateVerificationCode;
import com.example.farinapizza.util.Hashing;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
// 註冊表單轉換成@Entity物件
public class RegisterForm {
    private String username;
    private String email;
    private String password;

    // Getter & Setter 一定要有，Spring 才能幫你塞值

    public Member toEntity() {
        Member member = new Member();
        member.setUsername(this.username);
        member.setEmail(this.email);
        member.setPassword(Hashing.hashing(this.password)); // 密碼加密
        member.setRegistered(GenerateVerificationCode.generateVerificationCode()); // 自動產生驗證碼
        return member;
    }
}
