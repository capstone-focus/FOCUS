package capstone.focus.service;

import capstone.focus.domain.*;
import capstone.focus.repository.ChapterRepository;
import capstone.focus.repository.MemberGenreRepository;
import capstone.focus.repository.MemberRepository;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        // TODO 해당하는 id의 회원이 없을 경우 예외 처리
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        List<MemberGenre> memberGenres = memberGenreRepository.findByMember(member);
        String genres = getGenreNames(memberGenres);

        // TODO 해당 seq의 챕터가 없을 경우 예외 처리
        ChapterId chapterId = new ChapterId(chapterSeq, bookId);
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow();
        String chapterDescription = chapter.getDescription();
        String bookTitle = chapter.getBook().getTitle();

        String recommendRequestMessage = ChatGptRequestStringConst.bookName + bookTitle + ChatGptRequestStringConst.chapterSummaryAnalysis + chapterDescription + ChatGptRequestStringConst.customerInfoPrefix + genres + ChatGptRequestStringConst.customerInfoSuffix;

        List<MultiChatMessage> messages = Arrays.asList(
                new MultiChatMessage("user", ChatGptRequestStringConst.userMessage),
                new MultiChatMessage("assistant", ChatGptRequestStringConst.assistantMessage),
                new MultiChatMessage("user", recommendRequestMessage));
        String responseMessage = chatgptService.multiChat(messages);
        System.out.println(responseMessage);
    }

    private String getGenreNames(List<MemberGenre> memberGenres) {
        List<String> genres = new ArrayList<>();
        memberGenres.forEach(memberGenre -> genres.add(memberGenre.getGenre().getName()));
        return String.join(", ", genres.toArray(new String[0]));
    }
}