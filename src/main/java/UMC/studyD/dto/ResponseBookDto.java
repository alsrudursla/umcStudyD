package UMC.studyD.dto;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.Category;
import UMC.studyD.domain.enums.BookStatus;
import UMC.studyD.domain.mapping.BookHashtag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBookDto {
    private String title;
    private String description;
    private BookStatus bookStatus;
    private int likeCount;
    private Category category;
    private List<BookHashtag> hashtag;

    public static ResponseBookDto responseBook(Book book) {
        ResponseBookDto bookDto = ResponseBookDto.builder()
                .title(book.getTitle())
                .description(book.getDescription())
                .bookStatus(book.getBookStatus())
                .likeCount(book.getLikeCount())
                .category(book.getCategory())
                .hashtag(book.getBookHashtags())
                .build();
        return bookDto;
    }
}
