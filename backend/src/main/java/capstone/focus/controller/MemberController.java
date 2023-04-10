package capstone.focus.controller;

import capstone.focus.dto.LoginRequest;
import capstone.focus.dto.Message;
import capstone.focus.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        if (memberService.isSignUp(loginRequest.getEmail())) {
            memberService.login(loginRequest);
            Message message = new Message("Login Success");
            return ResponseEntity.ok(message);
        }

        //return ResponseEntity.ok().body(memberService.signUp(loginRequest)).build();
        return ResponseEntity.ok().build();
    }
}
