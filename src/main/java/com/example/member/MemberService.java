package com.example.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Integer addMember(Member member) {
        member.setPassword( (new BCryptPasswordEncoder()).encode(member.getPassword()) ); // 使用BCrypt加密
        Member newMember = memberRepository.save(member); // save()為UserRepository的抽象方法，但由於@EnableJpaRepositories會自動實作出來
        return newMember.getId();
    }

    public Member isEmailAvailable(String email) {
        return memberRepository.findByEmail(email); // 如果==null，會回傳true
    }

    public boolean isLogin() { // SecurityContextHolder 會負責維護當前使用者的相關資訊，登入的條件就是 Authentication 不為NULL，而且身分不為匿名身分(Anonymous Authentication)。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication == null || authentication instanceof AnonymousAuthenticationToken);
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}