package capstone.focus.service;

import capstone.focus.domain.*;
import capstone.focus.dto.chatgpt.SongRecommendation;
import capstone.focus.dto.chatgpt.SongRecommendationResponse;
import capstone.focus.repository.ChapterRepository;
import capstone.focus.repository.MemberGenreRepository;
import capstone.focus.repository.MemberRepository;
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

    public void recommendWithBookChapter(Long memberId, Long bookId, int chapterSeq) {
        String genres = getPreferredGenresOf(memberId);

        Chapter chapter = getChapter(bookId, chapterSeq);
        String chapterDescription = chapter.getDescription();
        String bookTitle = chapter.getBook().getTitle();

        String recommendRequestMessage = ChatGptRequestConst.bookName + bookTitle + ChatGptRequestConst.chapterSummaryAnalysis + chapterDescription + ChatGptRequestConst.customerInfoPrefix + genres + ChatGptRequestConst.customerInfoSuffix;

        List<MultiChatMessage> messages = Arrays.asList(
                new MultiChatMessage("user", ChatGptRequestConst.userMessage),
                new MultiChatMessage("assistant", ChatGptRequestConst.assistantMessage),
                new MultiChatMessage("user", recommendRequestMessage));
        String responseMessage = chatgptService.multiChat(messages);
        parseJsonToObject(responseMessage);
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

    private void parseJsonToObject(String responseMessage) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            SongRecommendationResponse response = objectMapper.readValue(responseMessage, SongRecommendationResponse.class);
            for (SongRecommendation song : response.getRecommendedSongs()) {
                log.info("Song Name: {}", song.getSongName());
                log.info("Artist: {}", song.getArtist());
                log.info("Spotify ID: {}", song.getSpotifyId());
                log.info("Reason: {}", song.getReason());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
