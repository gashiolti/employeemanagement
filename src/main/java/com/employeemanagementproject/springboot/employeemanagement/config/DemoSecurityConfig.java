package com.employeemanagementproject.springboot.employeemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    // add a reference to our security data source

    @Autowired
    @Qualifier("securityDataSource")
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // use jdbc authentication
        auth.jdbcAuthentication().dataSource(securityDataSource);

//        auth.jdbcAuthentication().dataSource(securityDataSource)
//                .usersByUsernameQuery(
//                        "SELECT username, password, enabled FROM users WHERE username=?")
//                .authoritiesByUsernameQuery(
//                        "SELECT username, 'ROLE_USER' FROM users WHERE username=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/employees").hasAnyRole("ADMIN")
                .antMatchers("/resources/**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username").passwordParameter("password")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();

    }


}
