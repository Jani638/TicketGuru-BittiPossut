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

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(toH2Console())
                .ignoringRequestMatchers("/api/**"))
            .httpBasic(Customizer.withDefaults());
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
