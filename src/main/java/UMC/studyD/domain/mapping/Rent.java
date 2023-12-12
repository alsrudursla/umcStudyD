package UMC.studyD.domain.mapping;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.Member;
import UMC.studyD.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // @Builder 사용 시, 생성자에 필요한 값이 자동 생성되므로, @NoArgsConstructor 를 사용하지 않아도 됨
@AllArgsConstructor
public class Rent extends BaseEntity {

    public Rent(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}
