package com.game.kalah.config;

import java.util.Arrays;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
//                  http .authorizeRequests().anyRequest().permitAll();
                  //    http.authorizeRequests().antMatchers("/", "/h2_console/**").permitAll();
                  /**
                   * If you're using Spring 4+, you need to deal with CSRF
                   * protection. This passes a token around to make sure that
                   * it's really the Javascript on your site that's doing the
                   * call. You can either turn it off with a simple config
                   * entry, or you have to add a few small things to your app;
                   * both to your page and the javascript.
                   */
                  http.cors().and().csrf().disable();

         }

}
