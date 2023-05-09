package capstone.focus.repository;

import capstone.focus.domain.Genre;
import capstone.focus.domain.Member;
import capstone.focus.domain.MemberGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberGenreRepository extends JpaRepository<MemberGenre, Long> {

    List<MemberGenre> findByMemberAndIsDeletedFalse(Member member);

    Optional<MemberGenre> findByMemberAndGenre(Member member, Genre genre);

    @Modifying
    @Query("update MemberGenre mg set mg.isDeleted = false where mg.id = :id")
    void addMemberGenre(@Param("id") Long id);
}
