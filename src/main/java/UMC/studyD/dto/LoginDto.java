package UMC.studyD.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LoginDto {

    private String email;
    private String password;
    private String message;

    @Builder
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
        this.message = null;
    }
}
