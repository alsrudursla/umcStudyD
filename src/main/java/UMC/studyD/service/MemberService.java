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
//}
