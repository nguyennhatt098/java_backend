package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
		  httpSecurity
          .cors().and().csrf().disable()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .authorizeRequests()
          .antMatchers(HttpMethod.GET, "/home/**","/validate/**").permitAll()
          .antMatchers(HttpMethod.PUT, "/post/**").permitAll()
          .antMatchers(HttpMethod.POST, "/post/**","/post/createComment","/post/createWish","post/createAnswerComment").permitAll()
          .and()
          .authorizeRequests()
          .antMatchers(HttpMethod.POST, "/login/login").permitAll()
          .anyRequest().authenticated()
          
          .and()
          .addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
          // disable page caching
          .headers().cacheControl();
	    }
	 
}
