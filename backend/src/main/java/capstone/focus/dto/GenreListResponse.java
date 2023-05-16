package capstone.focus.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GenreListResponse {

    private final List<String> genres;

    public GenreListResponse(List<String> genres) {
        this.genres = genres;
    }
}
