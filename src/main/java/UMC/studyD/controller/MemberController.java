package UMC.studyD.controller;

import UMC.studyD.dto.SignInDto;
import UMC.studyD.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원 가입
    @PostMapping("/user/signin")
    public void register(@RequestBody SignInDto signInDto) {
        memberService.signIn(signInDto);
    }
}
