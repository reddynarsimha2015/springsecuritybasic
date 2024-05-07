package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nt.entity.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	@Autowired
	MyUserDetailService useService;
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	       
	    	http.csrf((c)->c.disable())
            .authorizeHttpRequests(authorize ->{
            	
            
            	authorize.requestMatchers("/home","/contact","/register/**").permitAll();
            	authorize .requestMatchers("/user/**").hasRole("USER");
            	authorize .requestMatchers("/admin/**").hasRole("ADMIN");
            	authorize.anyRequest().authenticated();
            }
            )
            .formLogin(formLogin->formLogin.permitAll());
           // .rememberMe(Customizer.withDefaults());

        return http.build();
	    
	    }
	    
//	    @Bean
//	    public UserDetailsService userDetailsService() {
//	    	String pwd="$2a$12$LQm6hpMbrEozGHCclGiBf.JPig4U4wF5uqXpZsK/T8e88iMo/VSsC";
//	    	UserDetails user = User.builder().username("yntr")
//	    	.password(pwd)
//	    	.roles("USER")
//	    	.build();
//	    	UserDetails admin = User.builder().username("admin")
//	    	    	.password("$2a$12$MBEEBF9SVdCoJ0cbRjdaX.wGD3xsYeolYT5rTO7tggarstXr2ZW4W")
//	    	    	.roles("ADMIN","USER")
//	    	    	.build();
//	    	return new InMemoryUserDetailsManager(user,admin);
//	    }
	    @Bean
	    public UserDetailsService userDetailsService() {
	    	return useService;
	    }
	    
	    @Bean
	    public AuthenticationProvider authenicationProvider() {
	    	DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
	    	dao.setUserDetailsService(useService);
	    	dao.setPasswordEncoder(passwordEncoder());
	    	return dao;
	    }
	    
	    @Bean
	     public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    
}
