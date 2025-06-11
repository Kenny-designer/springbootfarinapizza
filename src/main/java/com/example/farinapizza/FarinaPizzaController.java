package com.example.farinapizza;

import com.example.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FarinaPizzaController {
    @Autowired
    private MemberService memberService;

    @GetMapping({"/", "/index"})
    public String viewHomePage(Model model, HttpServletRequest request) {
        model.addAttribute("name", (memberService.getUsername()));

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("parameterName", csrfToken.getParameterName());
        model.addAttribute("token", csrfToken.getToken());
        return "index";
    }

    @GetMapping({"/login"})
    public String viewLoginPage(Model model,
                                HttpServletRequest request) {
        if ( memberService.isLogin() ) {
            return "redirect:/";
        }
        else {
            model.addAttribute("title", "登入");

            CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
            model.addAttribute("parameterName", csrfToken.getParameterName());
            model.addAttribute("token", csrfToken.getToken());
            return "member";
        }
    }

    @GetMapping({"/updatePwd"})
    public String UpdatePwdPage() {
        return "updatePwd";
    }

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
