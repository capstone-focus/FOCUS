package capstone.focus.dto;

import capstone.focus.domain.Book;
import lombok.Getter;

@Getter
public class BookDetailResponse {
    private final String title;
    private final String author;
    private final String description;
    private final int chapterNumber;

    public BookDetailResponse(Book book, int chapterNumber) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.description = book.getDescription();
        this.chapterNumber = chapterNumber;
    }
}
