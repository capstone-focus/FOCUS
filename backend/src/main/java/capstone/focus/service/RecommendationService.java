package capstone.focus.service;

import capstone.focus.domain.*;
import capstone.focus.dto.Track;
import capstone.focus.dto.TrackListResponse;
import capstone.focus.dto.chatgpt.SongRecommendation;
import capstone.focus.dto.chatgpt.SongRecommendationResponse;
import capstone.focus.repository.ChapterRepository;
import capstone.focus.repository.MemberGenreRepository;
import capstone.focus.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final ChatgptService chatgptService;

    private final MemberRepository memberRepository;
    private final MemberGenreRepository memberGenreRepository;
    private final ChapterRepository chapterRepository;

    public TrackListResponse recommendWithBookChapter(Long memberId, Long bookId, int chapterSeq) {
        String genres = getPreferredGenresOf(memberId);

        Chapter chapter = getChapter(bookId, chapterSeq);
        String chapterDescription = chapter.getDescription();
        String bookTitle = chapter.getBook().getTitle();
        String recommendRequestMessage = ChatGptRequestConst.bookName + bookTitle + ChatGptRequestConst.chapterSummaryAnalysis + chapterDescription + ChatGptRequestConst.customerInfoPrefix + genres + ChatGptRequestConst.customerInfoSuffix;

        String responseMessage = sendChatGptRequest(recommendRequestMessage);

        return getTrackList(responseMessage);
    }

    private String getPreferredGenresOf(Long memberId) {
        // TODO 해당하는 id의 회원이 없을 경우 예외 처리
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        List<MemberGenre> memberGenres = memberGenreRepository.findByMember(member);
        return getGenreNames(memberGenres);
    }

    private String getGenreNames(List<MemberGenre> memberGenres) {
        List<String> genres = new ArrayList<>();
        memberGenres.forEach(memberGenre -> genres.add(memberGenre.getGenre().getName()));
        return String.join(", ", genres.toArray(new String[0]));
    }

    private Chapter getChapter(Long bookId, int chapterSeq) {
        ChapterId chapterId = new ChapterId(chapterSeq, bookId);
        // TODO 해당 seq의 챕터가 없을 경우 예외 처리
        return chapterRepository.findById(chapterId)
                .orElseThrow();
    }

    private String sendChatGptRequest(String recommendRequestMessage) {
        List<MultiChatMessage> messages = Arrays.asList(
                new MultiChatMessage("user", ChatGptRequestConst.userMessage),
                new MultiChatMessage("assistant", ChatGptRequestConst.assistantMessage),
                new MultiChatMessage("user", recommendRequestMessage));
        return chatgptService.multiChat(messages);
    }

    private TrackListResponse getTrackList(String responseMessage) {
        SongRecommendationResponse response = new SongRecommendationResponse();
        try {
            response = parseJsonToObject(responseMessage);
        } catch (IOException e) {
            // TODO 예외처리
            e.printStackTrace();
        }

        List<Track> tracks = new ArrayList<>();

        for (SongRecommendation song : response.getRecommendedSongs()) {
            tracks.add(new Track(song));

            log.info("Song Name: {}", song.getSongName());
            log.info("Artist: {}", song.getArtist());
            log.info("Reason: {}", song.getReason());
        }

        return new TrackListResponse(tracks);
    }

    private SongRecommendationResponse parseJsonToObject(String responseMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseMessage, SongRecommendationResponse.class);
    }
}
