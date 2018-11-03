package com.assignment.kalah.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Nesrin
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

         @Override
         protected void configure(HttpSecurity http) throws Exception {
                  // For example: Use only Http Basic and not form login.
                  http
                          .authorizeRequests().anyRequest().permitAll();
              //    http.authorizeRequests().antMatchers("/", "/h2_console/**").permitAll();
         }
}
