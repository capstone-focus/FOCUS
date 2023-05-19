package capstone.focus.controller;

import capstone.focus.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/books/{bookId}/chapters/{chapterSeq}/recommendations")
    public ResponseEntity<?> recommendTracksWithBookChapter(@SessionAttribute(name = "member") Long memberId,
                                                        @PathVariable Long bookId,
                                                        @PathVariable int chapterSeq) {
        return ResponseEntity.ok(recommendationService.recommendWithBookChapter(memberId, bookId, chapterSeq));
    }
}
