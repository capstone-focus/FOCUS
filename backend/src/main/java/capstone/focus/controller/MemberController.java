package capstone.focus.controller;

import capstone.focus.dto.GenreListRequest;
import capstone.focus.dto.LoginRequest;
import capstone.focus.dto.Message;
import capstone.focus.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {
        Message message = new Message(memberService.isSignUp(loginRequest.getEmail()) ? "Login Success" : "First Login Success");
        Long memberId = memberService.loginOrSignUp(loginRequest);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok(new Message("Logout Success"));
    }

    @PostMapping("/profile/music-genres")
    public ResponseEntity<?> registerMusicGenrePreference(@RequestBody @Valid GenreListRequest genreListRequest,
                                                          HttpServletRequest request) {

        String email = request.getHeader("email");
        memberService.registerGenres(email, genreListRequest.getGenres());
        return ResponseEntity.ok(new Message("success"));
    }

    @GetMapping("/profile/music-genres")
    public ResponseEntity<?> getMusicGenrePreference(HttpServletRequest request) {
        String email = request.getHeader("email");
        return ResponseEntity.ok(memberService.getGenres(email));
    }
}
