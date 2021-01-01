package com.bilgeadam.tutorial.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/welcome").permitAll()
                .antMatchers("/employees/**").hasRole("EMPLOYEE")
                .antMatchers("/employees/save").hasRole("MANAGER")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    //.loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/employees/list")
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                .logoutSuccessUrl("/welcome");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("test").password(passwordEncoder().encode("test")).roles("EMPLOYEE")
                .and()
                .withUser("mary").password(passwordEncoder().encode("test")).roles("EMPLOYEE", "MANAGER")
                .and()
                .withUser("susan").password(passwordEncoder().encode("test")).roles("EMPLOYEE", "ADMIN");
    }
}
