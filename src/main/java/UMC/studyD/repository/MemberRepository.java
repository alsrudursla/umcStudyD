package UMC.studyD.repository;

import UMC.studyD.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원 가입 - 중복 회원 검증
    boolean existsByEmail(String email);

    // 로그인 - DB 기반
    Member findByEmail(String email);
}
