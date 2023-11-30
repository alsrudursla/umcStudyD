//package UMC.studyD.config;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity // security 활성화
//public class SecurityConfig {
//
////    @Bean
////    public WebSecurityCustomizer webSecurityCustomizer() {
////        // 정적 자원에 스프링 시큐리티 필터 규칙을 적용하지 않도록 설정
////        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
////    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        // 비밀번호를 그대로 저장하지 않고 해시 함수를 이용하여 암호화 처리
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                // 경로 접근 권한 설정
//                .authorizeHttpRequests((auth) -> auth
//                        // 적은 순서대로 적용됨 (상단 -> 하단)
//
//                        // .requestMatchers : 특정 경로에 대한 권한 설정
//                            // 루트 경로 & 로그인 경로 -> 모두가 접근 가능해야 함 : permitAll
//                            // permitAll : 설정한 경로로의 접근을 인증 절차 없이 모두 허용
//                        .requestMatchers("/", "/user/login", "/user/signin").permitAll()
//
//                        // /admin 경로는 ADMIN 역할을 지닌 사람만 접근 가능
//                        //.requestMatchers("/admin").hasRole("ADMIN")
//
//                        // ** 는 모든 경로 허용
//                        // 설정한 경로로의 접근은 설정한 권한을 가진 사용자만 허용 : hasAnyRole
//                        //.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
//
//                        // 그 외 나머지 리소스는 인증된(로그인한) 사용자만 접근 가능 : anyRequest
//                        .anyRequest().authenticated()
//                );
//
//        http
//                // 로그인 처리 설정
//                .formLogin((auth) -> auth
//                        //.loginPage("/login") // 로그인 페이지 - 설정하지 않으면 기본 로그인 페이지 사용
//                        .loginProcessingUrl("/book") // 인증(로그인) 성공 시 이동하는 페이지
//                        .permitAll()
//                );
//
//        http
//                // 다중 로그인 설정 : 동일한 아이디로 다중 로그인 시 세션 통제
//                .sessionManagement((auth) -> auth
//                        .maximumSessions(1) // 하나의 아이디에 대한 다중 로그인 허용 개수
//                        .maxSessionsPreventsLogin(true)); // 다중 로그인 개수 초과 시 처리 방법
//                        // true : 초과 시 새로운 로그인 차단
//                        // false : 초과 시 기존 세션 하나 삭제
//
//        http
//                // 세션 고정 공격 보호를 위한 로그인 성공 시 세션 설정 방법
//                .sessionManagement((auth) -> auth
//                        .sessionFixation().changeSessionId()); // 로그인 시 동일한 세션에 대한 id 변경
//                        // .sessionFixation().none() : 로그인 시 세션 정보 변경 X
//                        // .sessionFixation().newSession() : 로그인 시 세션 새로 생성
//
//        http
//                // csrf 설정 (스프링 시큐리티 사용 시 기본으로 enable 적용됨)
//                    // enable 설정 시 스프링 시큐리티는 CsrfFilter 로 POST, PUT, DELETE 요청 토큰 검증
//                    // 앱에서 사용하는 API 서버의 경우 보통 세션을 STATELESS로 관리하기 때문에 스프링 시큐리티 csrf enable 설정을 진행하지 않아도 됨
//                .csrf((auth) -> auth.disable());
//
//        return http.build();
//    }
//}
