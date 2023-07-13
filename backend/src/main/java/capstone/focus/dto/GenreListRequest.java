package capstone.focus.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class GenreListRequest {

    @NotNull
    private List<String> genres;
}
