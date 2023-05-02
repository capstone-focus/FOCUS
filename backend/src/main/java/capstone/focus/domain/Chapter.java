package capstone.focus.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(ChapterId.class)
@Table(name = "chapter")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chapter {

    @Id
    @Column(name = "chapter_seq")
    private int seq;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private String description;
}
