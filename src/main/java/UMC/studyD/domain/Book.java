package UMC.studyD.domain;

import UMC.studyD.domain.common.BaseEntity;
import UMC.studyD.domain.enums.BookStatus;
import UMC.studyD.domain.mapping.BookHashtag;
import UMC.studyD.domain.mapping.Likes;
import UMC.studyD.domain.mapping.Rent;
import UMC.studyD.dto.AddBookDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    private int likeCount;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rent> rents = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookHashtag> bookHashtags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Book createBook(AddBookDto addBookDto) {
        Book book = Book.builder()
                .title(addBookDto.getTitle())
                .description(addBookDto.getDescription())
                .bookStatus(BookStatus.AVAILABLE)
                .likeCount(0)
                .build();
        return book;
    }

    public void changeBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    // 좋아요 추가
    public void addLikesCount() {
        this.likeCount++;
    }

    // 좋아요 취소
    public void deleteLikesCount() {
        this.likeCount--;
    }
}
