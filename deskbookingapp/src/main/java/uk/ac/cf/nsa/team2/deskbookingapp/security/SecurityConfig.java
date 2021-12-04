package uk.ac.cf.nsa.team2.deskbookingapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Create Web Security for Login and Logout flows.
 * Added redirection after logout. Navigating to homepage after logout
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/css/**").permitAll()
                .mvcMatchers("/font/**").permitAll()
                .mvcMatchers("/img/**").permitAll()
                .mvcMatchers("/js/mdb.min.js").permitAll()
                .mvcMatchers("/js/jquery.min.3.4.1.js").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/api/admin/**").hasRole("ADMIN")
                .mvcMatchers("/book").hasRole("USER")
                .mvcMatchers("/booking/**").hasRole("USER")
                .mvcMatchers("/file/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
        http.cors().and().csrf().disable();
    }

    /**
     * Adding user roles and their details for in memory auth.
     * Added in memory users as a dummy data to temporarily test the webpages and forms
     */

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN")
                .and()
                .withUser("user1").password("{noop}user1").roles("USER")
                .and()
                .withUser("user2").password("{noop}user2").roles("USER");
    }

}

