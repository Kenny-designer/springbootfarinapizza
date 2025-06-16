package com.example.farinapizza.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration //告訴 Spring Boot 這是一個「配置類別」，通常集中放置多個  @Bean 方法
public class WebSecurityConfig { // Spring Security 的設定檔
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService); // 須代入 interface UserDetailsService 的實作
        // 設定驗證的方式，會自動呼叫loadUserByUsername()
        authProvider.setPasswordEncoder( new BCryptPasswordEncoder() );
        // 須代入 interface PasswordEncoder 的實作， Spring Security 已將 BCryptPasswordEncoder 實作完成 interface PasswordEncoder
        // 設定密碼加密的方式，會把輸入的密碼用同樣的雜湊方式再加密一次
        return authProvider;
    }

    @Bean // filterChain 過濾器 為驗證流程的設定
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider()); // 設定所有請求都需要進行驗證
        http
                //.csrf(csrf -> csrf.disable()) // csrf 在每一次有可能改變資料的POST提交(或是PUT、DELETE)，會在session自動產生一個隨機的 CSRF Token，每次請求新的頁面，Token 可能不同
                // 因此註冊POST提交表單亦會檢查 CSRF Token
                .authorizeHttpRequests(auth -> auth // lambda表達式，只適用於原本為[介面且具單一抽象方法]， [->的左側]為抽象方法的參數；[->的右側]為抽象方法的具體實作
                        .requestMatchers("/booking").authenticated() // 只有"/booking"須經驗證通過才可放行
                        .anyRequest().permitAll() // 其它路徑的請求直接放行
                )
                .formLogin(form -> form.loginPage("/login").permitAll()
                        // 此處為 Spring Security 會攔截 POST "/login"，(controller無須撰寫)，自動把表單的 username 和 password 讀出來
                        .defaultSuccessUrl("/", true)
                        // 登入成功跳轉到 "/"，並在server端設定一個 session 為已通過authenticationProvider驗證 for 該使用者的
                        .failureUrl("/login?error")  // 登入失敗，回傳參數"error"
                )
                .logout(logout -> logout
                                // 此處為 Spring Security 會攔截 POST "/logout"，(controller無須撰寫)，將Session 中的 SecurityContext 清除
                                .logoutSuccessUrl("/index?meg").permitAll()
                        //登出成功後，會自動 redirect 到 "/login"
                );
        return http.build();
    }
}
