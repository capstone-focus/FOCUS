package capstone.focus.dto;

import capstone.focus.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {

    @NotNull
    private String name;
    @NotNull
    private String email;

    public Member toMember() {
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }
}
