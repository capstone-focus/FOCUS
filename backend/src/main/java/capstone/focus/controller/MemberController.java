package capstone.focus.controller;

import capstone.focus.dto.LoginRequest;
import capstone.focus.dto.Message;
import capstone.focus.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {
        Message message = new Message(memberService.isSignUp(loginRequest.getEmail()) ? "Login Success" : "First Login Success");

        Long memberId = memberService.loginOrSignUp(loginRequest);
        HttpSession session = request.getSession();
        session.setAttribute("member", memberId);

        return ResponseEntity.ok(message);
    }
}
