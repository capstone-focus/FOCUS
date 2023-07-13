package capstone.focus.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TrackListResponse {
    private final List<Track> tracks;

    public TrackListResponse(List<Track> tracks) {
        this.tracks = tracks;
    }
}
