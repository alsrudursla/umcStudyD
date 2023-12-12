package UMC.studyD.repository;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.Member;
import UMC.studyD.domain.mapping.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    boolean existsByMemberAndBook(Member member, Book book);
}
