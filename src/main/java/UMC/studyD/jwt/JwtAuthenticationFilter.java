package UMC.studyD.jwt;

import UMC.studyD.domain.Member;
import UMC.studyD.global.CustomUserDetail;
import UMC.studyD.repository.MemberRepository;
import UMC.studyD.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Order(0)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = parseBearerToken(request);
        CustomUserDetail member = parseMemberSpecification(token);
        AbstractAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.authenticated(member, token, member.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    // 토큰값 내의 회원ID & 회원Nickname (원래는 회원타입) 토대로 스프링 시큐리티에서 사용할 Member 객체 반환
    private CustomUserDetail parseMemberSpecification(String token) {
        String[] split = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 10)
                .map(tokenProvider::validateTokenAndGetSubject)
                .orElse("anonymous:anonymous")
                .split(":");

        Member member = memberRepository.findById(Long.parseLong(split[0])).orElseThrow(RuntimeException::new);
        CustomUserDetail customUserDetail = new CustomUserDetail(member);
        customUserDetail.setJwt(split[0], "", List.of(new SimpleGrantedAuthority(split[1])));
        return customUserDetail;
        // split[0] : 사용자 식별자
        // split[1] : 권한 정보
        // 비밀번호는 로그인 API 호출 시 이미 확인을 했기 때문에, Member 객체를 생성할 때는 사용하지 않으므로 빈 문자열을 넘긴다
    }
}
