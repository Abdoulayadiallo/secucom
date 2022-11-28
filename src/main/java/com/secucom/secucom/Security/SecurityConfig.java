package com.secucom.secucom.Security;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Service.AccountService;
import com.secucom.secucom.filters.JwtAuthentication;
import com.secucom.secucom.filters.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.ArrayList;
import java.util.Collection;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AccountService accountService;

    public SecurityConfig(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppCollaborateur appCollaborateur = accountService.loadCollabByUsername(username);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                appCollaborateur.getAppRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRoleName())));
                return new User(appCollaborateur.getUsername(),appCollaborateur.getPassword(),authorities);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();
        http.cors().and().csrf().disable();
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin();
        //http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/collaborateur/**").hasAuthority("ADMIN");
        //http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/collaborateur/**").hasAnyAuthority("ADMIN","USER");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.oauth2Login();
        //http.headers().frameOptions().disable();
        http.addFilter(new JwtAuthentication(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
