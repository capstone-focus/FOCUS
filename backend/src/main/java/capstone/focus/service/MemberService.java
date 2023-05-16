package capstone.focus.service;

import capstone.focus.domain.Genre;
import capstone.focus.domain.Member;
import capstone.focus.domain.MemberGenre;
import capstone.focus.dto.GenreListResponse;
import capstone.focus.dto.LoginRequest;
import capstone.focus.repository.GenreRepository;
import capstone.focus.repository.MemberGenreRepository;
import capstone.focus.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;
    private final MemberGenreRepository memberGenreRepository;

    public boolean isSignUp(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.isPresent();
    }

    public Long loginOrSignUp(LoginRequest loginRequest) {
        if (isSignUp(loginRequest.getEmail())) {
            return login(loginRequest);
        }

        return signUp(loginRequest);
    }

    public Long login(LoginRequest loginRequest) {
        // TODO 해당하는 이메일이 없을 경우 예외 처리
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        member.update(loginRequest.getName());

        return member.getId();
    }

    public Long signUp(LoginRequest loginRequest) {
        Member member = loginRequest.toMember();
        memberRepository.save(member);

        return member.getId();
    }

    public void registerGenres(Long memberId, List<String> newGenres) {
        // TODO 해당하는 id의 회원이 없을 경우 예외 처리
        Member member = memberRepository.findById(memberId)
                .orElseThrow();

        deleteExistingGenres(newGenres, member);
        updateWithNewGenres(newGenres, member);
    }

    private void deleteExistingGenres(List<String> newGenres, Member member) {
        List<MemberGenre> existingMemberGenres = memberGenreRepository.findByMember(member);
        existingMemberGenres.stream()
                .filter(existingMemberGenre -> !newGenres.contains(existingMemberGenre.getGenre().getName()))
                .forEach(memberGenreRepository::delete);
    }

    private void updateWithNewGenres(List<String> newGenres, Member member) {
        for (String name : newGenres) {
            Genre newGenre = genreRepository.findByName(name);

            if (memberGenreRepository.findByMemberAndGenre(member, newGenre).isEmpty()) {
                memberGenreRepository.save(MemberGenre.builder()
                        .member(member)
                        .genre(newGenre)
                        .build());
            }
        }
    }

    public GenreListResponse getGenres(Long memberId) {
        // TODO 해당하는 id의 회원이 없을 경우 예외 처리
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        List<MemberGenre> memberGenres = memberGenreRepository.findByMember(member);

        return new GenreListResponse(getGenreNames(memberGenres));
    }

    private List<String> getGenreNames(List<MemberGenre> memberGenres) {
        List<String> genres = new ArrayList<>();
        memberGenres.forEach(memberGenre -> genres.add(memberGenre.getGenre().getName()));
        return genres;
    }
}
