package capstone.focus.dto;

import capstone.focus.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {
    private Long bookId;
    private String title;
    private String author;
    private String description;

    public BookResponse(Book book) {
        this.bookId = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.description = book.getDescription();
    }
}
