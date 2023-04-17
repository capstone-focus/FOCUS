package capstone.focus.dto;

import capstone.focus.domain.Book;
import lombok.Getter;

@Getter
public class BookResponse {
    private final Long bookId;
    private final String title;
    private final String author;
    private final String description;

    public BookResponse(Book book) {
        this.bookId = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.description = book.getDescription();
    }
}
