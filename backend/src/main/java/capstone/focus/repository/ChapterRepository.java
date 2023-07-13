package capstone.focus.repository;

import capstone.focus.domain.Book;
import capstone.focus.domain.Chapter;
import capstone.focus.domain.ChapterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChapterRepository extends JpaRepository<Chapter, ChapterId> {

    @Query("select count(*) from Chapter ch where ch.book = :book")
    int chapterNumberOf(@Param("book")Book book);
}
