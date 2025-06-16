package com.example.farinapizza.service;

import com.example.farinapizza.dto.RegisterForm;
import com.example.farinapizza.entity.Member;
import com.example.farinapizza.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    // 檢查 Email 是否已被註冊
    public boolean checkEmailAvailable(String email) {
        return memberRepository.findByEmail(email) == null; // 若為null，則為true；反之則false
    }

    // 註冊
    public boolean addMember(RegisterForm form) {
        Member member = form.toEntity();
        if ( checkEmailAvailable(member.getEmail()) ) {
            try {
                memberRepository.save(member);
                return true;
            }
            // if Email duplicated；必須先在@Entity中，該欄位加入(unique = true)
            catch (DataIntegrityViolationException e) { return false; }
        }
        else { return false; }
    }

    // 檢查號是否存在
    public Member isEmailAvailable(String email) {
        return memberRepository.findByEmail(email); // 如果==null，會回傳true
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