package UMC.studyD.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // security 활성화
public class SecurityConfig {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        // 정적 자원에 스프링 시큐리티 필터 규칙을 적용하지 않도록 설정
//        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // 비밀번호를 그대로 저장하지 않고 해시 함수를 이용하여 암호화 처리
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 경로 접근 권한 설정
                .authorizeHttpRequests((auth) -> auth
                        // 적은 순서대로 적용됨 (상단 -> 하단)

                        // .requestMatchers : 특정 경로에 대한 권한 설정
                            // 루트 경로 & 로그인 경로 -> 모두가 접근 가능해야 함 : permitAll
                            // permitAll : 설정한 경로로의 접근을 인증 절차 없이 모두 허용
                        .requestMatchers("/", "/user/login", "/user/signin").permitAll()

                        // /admin 경로는 ADMIN 역할을 지닌 사람만 접근 가능
                        //.requestMatchers("/admin").hasRole("ADMIN")

                        // ** 는 모든 경로 허용
                        // 설정한 경로로의 접근은 설정한 권한을 가진 사용자만 허용 : hasAnyRole
                        //.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")

                        // 그 외 나머지 리소스는 인증된(로그인한) 사용자만 접근 가능 : anyRequest
                        .anyRequest().authenticated()
                );

        http
                // 로그인 처리 설정
                .formLogin((auth) -> auth
                        //.loginPage("/login") // 로그인 페이지 - 설정하지 않으면 기본 로그인 페이지 사용
                        .loginProcessingUrl("/book") // 인증(로그인) 성공 시 이동하는 페이지
                        .permitAll()
                );

        http
                // csrf 설정 (스프링 시큐리티 사용 시 기본으로 적용됨)
                .csrf((auth) -> auth.disable());

        return http.build();
    }
}
