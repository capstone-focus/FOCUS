package capstone.focus.dto.chatgpt;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SongRecommendationResponse {

    private List<SongRecommendation> recommendedSongs;
}
