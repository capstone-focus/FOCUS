package capstone.focus.controller;

import capstone.focus.service.BookService;
import capstone.focus.service.LocalFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;
    private final LocalFileService localFileService;

    @GetMapping("/books")
    public ResponseEntity<?> bookList(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(bookService.bookList(page));
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> bookDetail(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.bookDetail(bookId));
    }

    @GetMapping("/books/{bookId}/image")
    public ResponseEntity<?> bookImage(@PathVariable Long bookId) {
        String bookTitle = bookService.getBookTitle(bookId);
        UrlResource bookImage = localFileService.getBookImage(bookTitle);

        if (bookImage != null) {
            return ResponseEntity.ok().
                    contentType(MediaType.IMAGE_JPEG)
                    .body(bookImage);
        }

        log.info("Not Found");
        return ResponseEntity.notFound().build();
    }
}
