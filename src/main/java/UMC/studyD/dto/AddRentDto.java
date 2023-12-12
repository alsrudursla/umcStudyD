package UMC.studyD.dto;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.Member;
import UMC.studyD.domain.mapping.Rent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRentDto {
    private Long memberId;
    private Long bookId;

    public static AddRentDto addRent(Rent rent) {
        AddRentDto rentDto = AddRentDto.builder()
                .memberId(rent.getMember().getId())
                .bookId(rent.getBook().getId())
                .build();
        return rentDto;
    }
}
