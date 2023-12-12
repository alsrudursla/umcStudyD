package UMC.studyD.dto;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.enums.BookStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBookListDto {
    private String title;
    private String description;
    private BookStatus bookStatus;
    private int likeCount;

    public static ResponseBookListDto responseBookList(Book book) {
        ResponseBookListDto bookDto = ResponseBookListDto.builder()
                .title(book.getTitle())
                .description(book.getDescription())
                .bookStatus(book.getBookStatus())
                .likeCount(book.getLikeCount())
                .build();
        return bookDto;
    }
}
