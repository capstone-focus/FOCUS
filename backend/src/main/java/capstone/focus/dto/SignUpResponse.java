package capstone.focus.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponse {

    private Long memberId;
    private String message;

    public SignUpResponse(Long memberId, String message) {
        this.memberId = memberId;
        this.message = message;
    }
}
