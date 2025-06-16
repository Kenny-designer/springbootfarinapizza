package com.example.farinapizza.controller;

import com.example.farinapizza.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FarinaPizzaController {
    @Autowired
    private MemberService memberService;

    @GetMapping({"/", "/index"})
    public String viewHomePage(Model model) {
        model.addAttribute("name", (memberService.getUsername()));
        return "index";
    }

    @GetMapping({"/login"})
    public String viewLoginPage(Model model) {
        if ( memberService.isLogin() ) {
            return "redirect:/";
        }
        else {
            return "member";
        }
    }

    @GetMapping({"/verificationMail"})
    public String viewVerificationMailPage() { return "mail_verification"; }

    @GetMapping({"/updatePwd"})
    public String viewUpdatePwdPage() { return "updatePwd"; }

    @GetMapping({"/store"})
    public String viewStorePage() {
        return "store";
    }

    @GetMapping({"/menu"})
    public String viewMenuPage() {
        return "menu";
    }

    @GetMapping({"/booking"})
    public String viewBookingPage() {
        return "booking";
    }

    @GetMapping({"/transition"})
    public String viewTransitionPage(@RequestParam(value = "meg") String meg,
                                     Model model) {
        model.addAttribute("meg", meg);
        return "transition";
    }
}
