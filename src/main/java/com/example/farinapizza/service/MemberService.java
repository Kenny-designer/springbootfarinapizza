package com.example.farinapizza.service;

import lombok.extern.slf4j.Slf4j;
import com.example.farinapizza.dto.RegisterForm;
import com.example.farinapizza.entity.Member;
import com.example.farinapizza.repository.MemberRepository;
import com.example.farinapizza.util.GenerateVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 檢查 Email 是否已被註冊
    public boolean checkEmailAvailable(String email) { // 獨立寫一個檢查帳號的方法，僅回傳 boolean 值 效能更佳
        return memberRepository.findByEmail(email) == null; // 若為null，則為true；反之則false
    }

    // 註冊
    public String addMemberAndSendVerificationMail(RegisterForm form) {
        Member member = form.toEntity( passwordEncoder.encode(form.getPassword()),
                GenerateVerificationCode.generateVerificationCode() ); // 加密且產生三位數驗證碼，執行 DTO
        if ( !checkEmailAvailable(member.getEmail()) ) { log.warn("註冊失敗，Email 已存在: {}", member.getEmail()); return "fail"; }
        try { memberRepository.save(member); emailService.sendVerificationMail(member.getRegistered(), member.getEmail()); }
        // if Email duplicated；必須先在@Entity中，該欄位加入(unique = true)
        catch (DataIntegrityViolationException e) { log.warn("資料庫儲存失敗：{}", e.getMessage(), e); return "DataIntegrityViolationException"; }
        catch (MailException e) { log.error("寄信失敗：{}", e.getMessage(), e); return "MailException"; }
        // log 訊息組合時才做字串操作，可減少 CPU 資源的浪費，提升效能
        return "success";
    }

    // 檢查號是否存在
    public Member isEmailAvailable(String email) {
        return memberRepository.findByEmail(email);
    }

    // 檢查是否為已登入
    public boolean isLogin() { // SecurityContextHolder 會負責維護當前使用者的相關資訊，登入的條件就是 Authentication 不為NULL，而且身分不為匿名身分(Anonymous Authentication)。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication == null || authentication instanceof AnonymousAuthenticationToken);
    }

    // 取得登入者的 Username
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}