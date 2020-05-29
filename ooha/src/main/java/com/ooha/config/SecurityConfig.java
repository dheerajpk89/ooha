package com.ooha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessValidateFilter accessValidateFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(accessValidateFilter, BasicAuthenticationFilter.class);
		http.authorizeRequests().antMatchers("/", "/js/**", "/css/**", "/img/**", "/two_i/**", "/webjars/**").permitAll();
		/*.antMatchers("/user/**").hasRole("USER")
		.anyRequest().authenticated();
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);*/

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("AuthenticationManagerBuilder"
				+ auth.toString());
		//eledgerServices.getUserDetail()user
		/* auth.inMemoryAuthentication()
		        .withUser("user").password("password").roles("doctor")
		    .and()
		        .withUser("manager").password("password").roles("patient");*/
	}


}
