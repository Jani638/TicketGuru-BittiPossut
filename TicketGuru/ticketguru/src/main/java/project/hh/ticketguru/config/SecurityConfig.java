package project.hh.ticketguru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/api/**", "/h2-console/**").authenticated()
                .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());


            http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
            http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
            http.formLogin(); 
            return http.build();
        
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
        .username("user")
        .password("{noop}password")
        .roles("USER")
        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
