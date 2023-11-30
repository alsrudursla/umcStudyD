package UMC.studyD.dto;

import UMC.studyD.domain.enums.MemberGender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInDto {
    private String email;
    private String password;
    private String nickname;
    private String phoneNum;
    private MemberGender gender;

    @Builder
    public SignInDto(String email, String password, String nickname, String phoneNum, MemberGender memberGender) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNum = phoneNum;
        this.gender = memberGender;
    }
}
