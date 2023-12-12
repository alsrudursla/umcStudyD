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
public class ResponseRentListDto {
    private String memberName;
    private String bookName;

    public static ResponseRentListDto responseRentList(Rent rent) {
        ResponseRentListDto rentListDto = ResponseRentListDto.builder()
                .memberName(rent.getMember().getNickname())
                .bookName(rent.getBook().getTitle())
                .build();
        return rentListDto;
    }
}