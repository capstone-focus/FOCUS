package capstone.focus.dto;

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
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;

}
