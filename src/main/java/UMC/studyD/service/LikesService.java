package UMC.studyD.service;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.Member;
import UMC.studyD.domain.mapping.Likes;
import UMC.studyD.dto.AddLikesCount;
import UMC.studyD.repository.BookRepository;
import UMC.studyD.repository.LikesRepository;
import UMC.studyD.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    // 좋아요 추가
    public HttpStatus addLikes(AddLikesCount addLikesCount) {
        Member member = memberRepository.findById(addLikesCount.getMemberId()).orElseThrow(RuntimeException::new);
        Book book = bookRepository.findById(addLikesCount.getBookId()).orElseThrow(RuntimeException::new);

        // 1. 테이블에 새로운 레코드 추가
        if (likesRepository.existsByMemberAndBook(member, book)) {
            // 이미 존재하는 레코드인 경우 추가 X 오류 코드 반환
            return HttpStatus.CONFLICT;
        } else {
            likesRepository.save(new Likes(member, book));

            // 2. book entity 의 likesCount +1
            book.addLikesCount();
            return HttpStatus.OK;
        }
    }

    // 좋아요 취소
    public void deleteLikes(Long id) {
        Likes likes = likesRepository.findById(id).orElseThrow(RuntimeException::new);
        Book book = bookRepository.findById(likes.getBook().getId()).orElseThrow(RuntimeException::new);

        // 1. 해당 Likes 테이블의 id 가진 레코드 삭제
        likesRepository.deleteById(id);

        // 2. book entity 의 likesCount -1
        book.deleteLikesCount();
    }
}
