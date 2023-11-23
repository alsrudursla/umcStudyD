package UMC.studyD.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum MemberGender {
    MALE, FEMALE;


    // enum 타입으로 dto 전달 받을 시 오류 해결
    // ex. 없는 타입을 요청한 경우, 소문자로 요청한 경우
    // https://inkyu-yoon.github.io/docs/Language/SpringBoot/EnumValidation
    @JsonCreator
    public static MemberGender parsing(String inputValue) {
        return Stream.of(MemberGender.values())
                // 소문자 입력 시 대문자로 반환 후 값 비교 -> 해당 값 있으면 값 반환, 없으면 null 반환
                .filter(memberGender -> memberGender.toString().equals(inputValue.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}