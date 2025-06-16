package com.example.farinapizza.security;

import com.example.farinapizza.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails { // UserDetails：已經定義好功能的介面，只需要實作它即可
    private final Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    } // 初始化將 user物件 代入

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // 回傳[有效帳號]；false則為[帳號過期]

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // 回傳[不鎖定帳號]；false則為[鎖定帳號]

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // 回傳[有效密碼]；false則為[過期密碼，需更改密碼等...]

    @Override
    public boolean isEnabled() {
        return true;
    } // 回傳[帳號可用]；false則為[帳號不可用]

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    } // 回傳[使用者權限]；null則為[沒有設定權限]

    @Override
    public String getPassword() {
        return member.getPassword();
    } // 回傳user的password，與輸入的password進行比對

    @Override
    public String getUsername() {
        return member.getEmail();
    } // 回傳user的username，與輸入的username進行比對
}

