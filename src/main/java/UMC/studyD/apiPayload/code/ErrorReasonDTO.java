package UMC.studyD.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorReasonDTO implements BaseErrorCode{

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    private final boolean isSuccess;

    @Override
    public ErrorReasonDTO getReason() {
        return null;
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return null;
    }
}
