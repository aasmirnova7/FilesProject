package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.CustomAuthenticationProvider;

//Возможности выхода-входа на стр.
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"spring"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/repeat_registration_error").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/Upload").permitAll()
                .antMatchers("/Success").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error")
                .and()
                .logout()
                .permitAll();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}