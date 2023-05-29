package capstone.focus.controller;

import capstone.focus.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/books/{bookId}/chapters/{chapterSeq}/recommendations")
    public ResponseEntity<?> recommendTracksWithBookChapter(HttpServletRequest request,
                                                            @PathVariable Long bookId,
                                                            @PathVariable int chapterSeq) {
        String email = request.getHeader("email");
        return ResponseEntity.ok(recommendationService.recommendWithBookChapter(email, bookId, chapterSeq));
    }
}
