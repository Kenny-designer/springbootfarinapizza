package com.example.farinapizza.security;

import com.example.farinapizza.entity.Member;
import com.example.farinapizza.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService { // UserDetailsService：也是已經定義好的介面
    @Autowired
    private MemberService memberService;

    @Override // 此方法須回傳UserDetails介面的物件
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberService.isEmailAvailable(email); // 此處是與資料庫進行[帳號比對]，並回傳一個 user物件
        if ( member == null ) {
            throw new UsernameNotFoundException("該帳號不存在");
            // UsernameNotFoundException，Spring Security 會把它解釋為「找不到帳號」，並自動在登入頁面顯示錯誤（例如 ?error）。
        }
        else {
            return new CustomUserDetails(member);
        }
    }
}