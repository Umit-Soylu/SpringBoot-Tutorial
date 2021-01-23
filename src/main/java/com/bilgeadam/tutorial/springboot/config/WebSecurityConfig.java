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
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test").password(passwordEncoder().encode("test")).roles(new String[]{"EMPLOYEE", "USER"})
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("demo").password(passwordEncoder().encode("test")).roles(new String[]{"ADMIN", "SUPER_ADMIN"})
                .and()
                .withUser("demo2").password(passwordEncoder().encode("test")).roles("ADMIN").authorities("EMPLOYEE_EDIT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable() //cross site request forgery
                .authorizeRequests()
                    .antMatchers("/hello").permitAll()
                    .antMatchers("/employees/create").hasRole("ADMIN")
                    .antMatchers("/employees/**").hasAnyRole(new String[]{"EMPLOYEE", "ADMIN"})
               // .antMatchers("/hello").not().hasRole("ADMIN")
                    .antMatchers("/").permitAll()
                .and()
                    .formLogin().permitAll()
                    .loginPage("/authentication/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout().permitAll()
                    .logoutUrl("/authentication/logout")
                    .logoutSuccessUrl("/")
                .and()
                    .rememberMe()
                    .rememberMeParameter("remember-me-parameter")
                    .tokenValiditySeconds(15*60)
                .and()
                    .sessionManagement().
                        maximumSessions(1).
                        maxSessionsPreventsLogin(true).
                        expiredUrl("/authentication/logout");
    }


}
