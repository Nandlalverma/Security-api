package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new UserDetailsServiceImpl();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
	    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	    
	    daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
	    daoAuthenticationProvider.setPasswordEncoder(passEncoder());
	    return daoAuthenticationProvider;
	}
	
	
	// configure method
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
		  auth.authenticationProvider(daoAuthenticationProvider());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

       http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").
       antMatchers("/user/**").hasRole("USER")
       .antMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
		   
		super.configure(http);
	}
	
	
	
	
	

}
