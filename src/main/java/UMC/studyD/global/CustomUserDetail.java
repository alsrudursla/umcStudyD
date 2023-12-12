//package UMC.studyD.global;
//
//import UMC.studyD.domain.Member;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//public class CustomUserDetail implements UserDetails {
//
//    private Member member;
//
//    public CustomUserDetail(Member member) {
//        this.member = member;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // 회원 role 값 반환
//        /*
//        Collection<GrantedAuthority> collection = new ArrayList<>();
//        collection.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return member.getRole();
//            }
//        });
//        return collection;
//         */
//
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return member.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return member.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
