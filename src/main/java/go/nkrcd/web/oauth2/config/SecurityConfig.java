package go.nkrcd.web.oauth2.config;

import go.nkrcd.web.oauth2.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    OAuthService oAuthService;

    @Autowired
    CustomOAuthSuccessHandler customOAuthSuccessHandler;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/", "/login", "/join", "/join/**", "/review/view", "/error").permitAll()
                .antMatchers("/crawler/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()

                // 접근 권한 없을 시 핸들러
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()

                .logout()
                .logoutSuccessUrl("/")
                .and()

                .oauth2Login()
                .loginPage("/login")
                .failureUrl("/login")
                .successHandler(customOAuthSuccessHandler)
                .userInfoEndpoint()
                .userService(oAuthService);

        // 중복 로그인 방지(하나의 세션만 허용)
        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/");

        return http.build();
    }
}