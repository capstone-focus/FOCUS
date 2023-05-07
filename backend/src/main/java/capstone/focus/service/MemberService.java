package capstone.focus.service;

import capstone.focus.domain.Member;
import capstone.focus.dto.LoginRequest;
import capstone.focus.dto.SignUpResponse;
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
        member.update(loginRequest.getName());
    }

    public SignUpResponse signUp(LoginRequest loginRequest) {
        Member member = loginRequest.toMember();
        memberRepository.save(member);

        Long memberId = member.getId();
        return new SignUpResponse(memberId, "First Login Success");
    }

}
