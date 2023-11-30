//package UMC.studyD.service;
//
//import UMC.studyD.domain.Member;
//import UMC.studyD.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member savedMember = memberRepository.findByEmail(email);
//
//        if (savedMember != null) {
//            return new CustomUserDetail(savedMember);
//        }
//
//        return null;
//    }
//}
