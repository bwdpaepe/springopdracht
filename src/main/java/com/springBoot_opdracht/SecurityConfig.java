package com.springBoot_opdracht;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
          .withUser("bartUser").password(encoder.encode("paswoord")).roles("USER").and()
          .withUser("bartAdmin").password(encoder.encode("paswoord")).roles("USER","ADMIN");
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().and()
                .authorizeHttpRequests(requests -> 
                		requests.requestMatchers("/login**").permitAll()
            	        		.requestMatchers("/css/**").permitAll()
            	        		.requestMatchers("/img/**").permitAll()
            	        		.requestMatchers("/*")
            	        		.access(new WebExpressionAuthorizationManager("hasRole('USER') or hasRole('ADMIN')")))
                .formLogin(form -> 
                		form.defaultSuccessUrl("/welcome", true)
                         	.loginPage("/login")
                         	.usernameParameter("email").passwordParameter("password"))
                ;
        
        return http.build();
    }
    
}
