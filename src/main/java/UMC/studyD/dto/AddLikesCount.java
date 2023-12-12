package UMC.studyD.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddLikesCount {
    private Long memberId;
    private Long bookId;
}
