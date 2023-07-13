package capstone.focus.dto;

import capstone.focus.dto.chatgpt.SongRecommendation;
import lombok.Getter;

@Getter
public class Track {

    private final String artist;
    private final String name;

    public Track(SongRecommendation song) {
        this.artist = song.getArtist();
        this.name = song.getSongName();
    }
}
