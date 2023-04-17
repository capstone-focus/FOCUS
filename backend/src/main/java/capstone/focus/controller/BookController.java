package capstone.focus.controller;

import capstone.focus.dto.BookListResponse;
import capstone.focus.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> bookList(@RequestParam(defaultValue = "0") int page) {
        BookListResponse bookListResponse = bookService.bookList(page);
        return ResponseEntity.ok(bookListResponse);
    }
}
