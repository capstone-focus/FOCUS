package capstone.focus.service;

import capstone.focus.domain.Book;
import capstone.focus.dto.BookDetailResponse;
import capstone.focus.dto.BookListResponse;
import capstone.focus.dto.BookResponse;
import capstone.focus.repository.BookRepository;
import capstone.focus.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private static final int size = 15;

    private final BookRepository bookRepository;
    private final ChapterRepository chapterRepository;

    public BookListResponse bookList(int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Book> books = bookRepository.findAll(pageable);

        List<BookResponse> booksResponse = books.stream()
                .map(book -> new BookResponse(book))
                .collect(Collectors.toList());

        return new BookListResponse(booksResponse);
    }

    public BookDetailResponse bookDetail(Long bookId) {
        // TODO 해당 id의 책이 없을 경우 예외 처리
        Book book = bookRepository.findById(bookId)
                .orElseThrow();
        int chapterCount = chapterRepository.chapterNumberOf(book);

        return new BookDetailResponse(book, chapterCount);
    }
}
