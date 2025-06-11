package com.example.farinapizza;

import com.example.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarinaPizzaRestController {
    @Autowired
    private MemberService memberService;

    @PostMapping({"/login"})
    public String login() {
        return "member";
    }

    @PostMapping({"/signup"})
    public String signup() {
        return "member";
    }

    @PostMapping({"/checkEmail"})
    public String checkEmail() {
        return "member";
    }

    @PostMapping({"/forgetPwd"})
    public String forgetPwd() {
        return "member";
    }
}
