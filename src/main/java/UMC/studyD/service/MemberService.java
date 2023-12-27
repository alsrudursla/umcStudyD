package UMC.studyD.service;

import UMC.studyD.domain.Member;
import UMC.studyD.dto.LoginDto;
import UMC.studyD.dto.SignInDto;
import UMC.studyD.global.CustomUserDetail;
import UMC.studyD.jwt.TokenProvider;
import UMC.studyD.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    // 회원 가입
    public void signIn(SignInDto signInDto) {

        // DB 에 동일한 이메일을 가진 사람이 있는지 검증
        boolean isMember = memberRepository.existsByEmail(signInDto.getEmail());
        if (isMember) {
            throw new RuntimeException("이미 존재하는 회원입니다");
        }

        // DB 에 회원 저장
        Member member = Member.createMember(signInDto, bCryptPasswordEncoder);
        memberRepository.save(member);
    }

    // 로그인
    public UserDetails login(LoginDto loginDto) throws UsernameNotFoundException {
        Member savedMember = memberRepository.findByEmail(loginDto.getEmail());

        if (savedMember != null) { // 데이터가 있음
            // 비밀번호 비교하기
            String inputPassword = loginDto.getPassword();
            String savedPassword = savedMember.getPassword();

            if (bCryptPasswordEncoder.matches(inputPassword, savedPassword)) {
                // 비밀번호 일치 + jwt 토큰 발급
                loginDto.setMessage("로그인 성공");
                String token = tokenProvider.createToken(String.format("%s:%s", savedMember.getId(), savedMember.getNickname()));
                savedMember.createToken(token);
                return new CustomUserDetail(savedMember);
            }
        }
        /* 웹으로 하면 적용되는데 postman 사용 시 비밀번호 비교가 자동으로 안된다
        if (savedMember != null) {
            loginDto.setMessage("로그인 성공");
            return new CustomUserDetail(savedMember);
        }
         */

        loginDto.setMessage("로그인 실패");
        return null;
    }
}
