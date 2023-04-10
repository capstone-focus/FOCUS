package capstone.focus.service;

import capstone.focus.domain.Member;
import capstone.focus.dto.LoginRequest;
import capstone.focus.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean isSignUp(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.isPresent();
    }

    public void login(LoginRequest loginRequest) {
        // TODO 해당하는 이메일이 없을 경우 예외 처리
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        member.update(loginRequest.getName(), loginRequest.getAccessToken(), loginRequest.getRefreshToken());
    }

    public void signUp(LoginRequest loginRequest) {
        // TODO 회원 가입하고 id와 success 메시지 반환
    }

}
