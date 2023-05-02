package capstone.focus.dto;

import lombok.Getter;

@Getter
public class ChapterDetailResponse {

    private final String description;

    public ChapterDetailResponse(String description) {
        this.description = description;
    }
}
