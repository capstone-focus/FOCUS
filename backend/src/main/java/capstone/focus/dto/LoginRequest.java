package capstone.focus.dto;

import capstone.focus.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class LoginRequest {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;

    public Member toMember() {
        return Member.builder()
                .name(name)
                .email(email)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(LocalDateTime.now().plusHours(1))
                .build();
    }
}
