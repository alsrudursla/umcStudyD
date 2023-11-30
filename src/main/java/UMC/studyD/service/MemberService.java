//package UMC.studyD.service;
//
//import UMC.studyD.domain.Member;
//import UMC.studyD.dto.SignInDto;
//import UMC.studyD.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    // 회원 가입
//    public void signIn(SignInDto signInDto) {
//
//        // DB 에 동일한 이메일을 가진 사람이 있는지 검증
//        boolean isMember = memberRepository.existsByEmail(signInDto.getEmail());
//        if (isMember) {
//            throw new RuntimeException("이미 존재하는 회원입니다");
//        }
//
//        // DB 에 회원 저장
//        Member member = Member.createMember(signInDto, bCryptPasswordEncoder);
//        memberRepository.save(member);
//    }
//
//    // 로그인
//    public UserDetails login(LoginDto loginDto) throws UsernameNotFoundException {
//        Member savedMember = memberRepository.findByEmail(loginDto.getEmail());
//
//        if (savedMember != null) { // 데이터가 있음
//            // 비밀번호 비교하기
//            String inputPassword = loginDto.getPassword();
//            String savedPassword = savedMember.getPassword();
//
//            if (bCryptPasswordEncoder.matches(inputPassword, savedPassword)) {
//                // 비밀번호 일치
//                loginDto.setMessage("로그인 성공");
//                return new CustomUserDetail(savedMember);
//            }
//        }
//        /* 웹으로 하면 적용되는데 postman 사용 시 비밀번호 비교가 자동으로 안된다
//        if (savedMember != null) {
//            loginDto.setMessage("로그인 성공");
//            return new CustomUserDetail(savedMember);
//        }
//         */
//
//        loginDto.setMessage("로그인 실패");
//        return null;
//    }
//}
