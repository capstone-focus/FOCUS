package capstone.focus.dto;

import capstone.focus.dto.chatgpt.SongRecommendation;
import lombok.Getter;

@Getter
public class Track {

    private final String artist;
    private final String id;
    private final String name;

    public Track(SongRecommendation song) {
        this.artist = song.getArtist();
        this.id = song.getSpotifyId();
        this.name = song.getSongName();
    }
}
