package capstone.focus.repository;

import capstone.focus.domain.Genre;
import capstone.focus.domain.Member;
import capstone.focus.domain.MemberGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberGenreRepository extends JpaRepository<MemberGenre, Long> {

    List<MemberGenre> findByMember(Member member);

    Optional<MemberGenre> findByMemberAndGenre(Member member, Genre genre);
}
