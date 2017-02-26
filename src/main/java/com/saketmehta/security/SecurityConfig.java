package com.saketmehta.security;

import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 11:29 AM
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationProvider   jwtAuthenticationProvider;

    public SecurityConfig(AuthenticationProvider jwtAuthenticationProvider) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.authenticationEntryPoint = new Http401AuthenticationEntryPoint("Unauthorized");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/**").authenticated()
                .and()
                .addFilterBefore(buildJwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl().disable();
    }

    private JwtAuthenticationProcessingFilter buildJwtAuthenticationProcessingFilter() throws Exception {
        AntPathRequestMatcher authMatcher = new AntPathRequestMatcher("/api/auth/**");
        JwtAuthenticationProcessingFilter filter = new JwtAuthenticationProcessingFilter(new NegatedRequestMatcher(authMatcher));
        filter.setAuthenticationManager(this.authenticationManagerBean());
        return filter;
    }
}
