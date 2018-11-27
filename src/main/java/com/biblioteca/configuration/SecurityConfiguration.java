package com.biblioteca.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
/*@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)*/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
		
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/","/mantenimiento","/css/**","/fonts/**","/imgs/**","/js/**","/autorizaciontesis/**","/listartesis/**","/images/**","/verPDF/**").permitAll()
		.anyRequest().authenticated();
	/*	.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
		.usernameParameter("username").passwordParameter("password")
		.defaultSuccessUrl("/loginsuccess").permitAll()
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
		.permitAll();*/
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
	
		UserBuilder users=User.withDefaultPasswordEncoder();
		
		build.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN"));
		
	}
	

}
