package capstone.focus.controller;

import capstone.focus.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> bookList(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(bookService.bookList(page));
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> bookDetail(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.bookDetail(bookId));
    }
}
