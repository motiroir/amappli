package isika.p3.amappli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import isika.p3.amappli.security.CustomAuthenticationEntryPoint;
import isika.p3.amappli.security.CustomAuthenticationFailureHandler;
import isika.p3.amappli.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	private final CustomAuthenticationEntryPoint entryPoint;
	private final CustomAuthenticationFailureHandler failureHandler;
	private final CustomAuthenticationSuccessHandler successHandler;


	public WebSecurityConfig(CustomAuthenticationEntryPoint entryPoint, CustomAuthenticationFailureHandler failureHandler, CustomAuthenticationSuccessHandler successHandler) {
		this.entryPoint = entryPoint;
		this.failureHandler = failureHandler;
		this.successHandler = successHandler;
	}
	


    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/login*","/*/login*").permitAll()
				.requestMatchers("/amap/amapHomePage","/platform").permitAll()
//				.requestMatchers("/**").authenticated() //all requests that match this need to be authenticated unless stated otherwise
				.anyRequest().permitAll() //all other requests don't need authentication
			)
			.exceptionHandling(exception -> exception.authenticationEntryPoint(entryPoint)) //send user to the appropriate login page
			.formLogin((form) -> form
				//.loginPage("/sectest/login") 
				.loginProcessingUrl("/login") //this is the post request endpoint for spring to process the login attempt
				//.failureUrl("/sectest/login?error=true") 
				.successHandler(successHandler)
				.failureHandler(failureHandler)
				.permitAll() //everyone has acces to the login page
			)
			.logout((logout) -> logout.logoutUrl("/logout").permitAll());
           // .csrf((csrf)-> csrf.disable()); //everyone can log out lol

		return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
    }
}
