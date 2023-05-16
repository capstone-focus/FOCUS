package capstone.focus.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "member_genre")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_genre_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Builder
    public MemberGenre(Member member, Genre genre) {
        this.member = member;
        this.genre = genre;
    }
}
