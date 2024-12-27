package isika.p3.amappli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
//				.requestMatchers("/sectest/**").authenticated() //all requests that match this need to be authenticated unless stated otherwise
				.anyRequest().permitAll() //all other requests don't need authentication
			)
			.formLogin((form) -> form
				.loginPage("/sectest/login")
				.loginProcessingUrl("/sectest/login")
				.failureUrl("/sectest/login?error=true") 
				.permitAll() //everyone has acces to the login page
			)
			.logout((logout) -> logout.logoutUrl("/sectest/logout").permitAll());
           // .csrf((csrf)-> csrf.disable()); //everyone can log out lol

		return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
    }
}
