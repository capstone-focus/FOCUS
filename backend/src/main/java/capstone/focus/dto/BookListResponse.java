package capstone.focus.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BookListResponse {

    private final List<BookResponse> books;

    public BookListResponse(List<BookResponse> books) {
        this.books = books;
    }
}
