package com.secure.with.jwt.securewithjwt.config;

import com.secure.with.jwt.securewithjwt.security.AuthEntryPoint;
import com.secure.with.jwt.securewithjwt.security.JWTAuthenticationTokenFilter;
import com.secure.with.jwt.securewithjwt.security.JwtAuthProvider;
import com.secure.with.jwt.securewithjwt.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@EnableGlobalMethodSecurity
@EnableWebSecurity
@Configuration
public class JwtBaseConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthProvider jwtAuthProvider;
    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(jwtAuthProvider));
    }

    @Bean
    public JWTAuthenticationTokenFilter jwtAuthenticationFilter() {
        JWTAuthenticationTokenFilter jwtAuthenticationFilter = new JWTAuthenticationTokenFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/rest/**")
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }
}
