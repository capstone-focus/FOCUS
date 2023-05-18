package capstone.focus.dto.chatgpt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRecommendation {

    private String songName;
    private String artist;
    private String spotifyId;
    private String reason;
}
