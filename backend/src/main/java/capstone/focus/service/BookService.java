package capstone.focus.service;

import capstone.focus.domain.Book;
import capstone.focus.dto.BookListResponse;
import capstone.focus.dto.BookResponse;
import capstone.focus.repository.BookRepository;
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

    private final BookRepository bookRepository;
    private static final int size = 15;

    public BookListResponse bookList(int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Book> books = bookRepository.findAll(pageable);

        List<BookResponse> booksResponse = books.stream()
                .map(book -> new BookResponse(book))
                .collect(Collectors.toList());

        return new BookListResponse(booksResponse);
    }
}
