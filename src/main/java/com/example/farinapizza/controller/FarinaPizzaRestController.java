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
    public boolean checkEmail(@RequestParam(value = "email") String email) { return memberService.checkEmailAvailable(email); }

    @PostMapping({"/signup"})
    public String signup(@RequestBody RegisterForm form) { return memberService.addMemberAndSendVerificationMail(form); }

    @PostMapping({"/forgetPwd"})
    public String forgetPwd() {
        return "member";
    }

    @PostMapping({"/updatePwd"})
    public String updatePwd() {
        return "member";
    }
}

//    @PostMapping("/signup")
//    public ResponseEntity<ApiResponse> signup(@RequestBody RegisterForm form) {
//        if (memberService.addMember(form)) {
//            return ResponseEntity.ok(new ApiResponse(true, "註冊成功"));
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ApiResponse(false, "註冊失敗"));
//        }
//    }

//package your.package.name; // 請依照你的專案路徑修改
//
//public class ApiResponse {
//    private boolean success;
//    private String message;
//
//    // 建構子
//    public ApiResponse(boolean success, String message) {
//        this.success = success;
//        this.message = message;
//    }
//
//    // Getter & Setter
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//}
