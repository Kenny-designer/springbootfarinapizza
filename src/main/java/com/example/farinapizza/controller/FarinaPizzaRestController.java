package com.example.farinapizza.controller;

import com.example.farinapizza.dto.RegisterForm;
import com.example.farinapizza.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarinaPizzaRestController {
    @Autowired
    private MemberService memberService;

    @PostMapping({"/checkEmail"})
    public boolean checkEmail(@RequestParam(value = "email") String email) {
        return memberService.checkEmailAvailable(email);
    }

    @PostMapping({"/signup"})
    public boolean signup(@RequestBody RegisterForm form) { return memberService.addMember(form); }



    @PostMapping({"/forgetPwd"})
    public String forgetPwd() {
        return "member";
    }

    @PostMapping({"/updatePwd"})
    public String updatePwd() {
        return "member";
    }
}
