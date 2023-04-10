package capstone.focus.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SignUpResponse {

    private final Long memberId;
    private final String message;

    public SignUpResponse(Long memberId, String message) {
        this.memberId = memberId;
        this.message = message;
    }
}