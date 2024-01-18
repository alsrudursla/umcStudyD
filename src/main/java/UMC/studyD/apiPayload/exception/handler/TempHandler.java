package UMC.studyD.apiPayload.exception.handler;

import UMC.studyD.apiPayload.code.BaseErrorCode;
import UMC.studyD.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
