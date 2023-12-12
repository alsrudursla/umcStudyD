package UMC.studyD.domain.mapping;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.Member;
import UMC.studyD.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Likes extends BaseEntity {

    public Likes(Member member, Book book) {
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
