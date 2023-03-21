package com.driagon.springboot.datajpa.app;

import com.driagon.springboot.datajpa.app.auth.filter.JWTAuthenticationFilter;
import com.driagon.springboot.datajpa.app.auth.filter.JWTAuthorizationFilter;
import com.driagon.springboot.datajpa.app.auth.handler.LoginSuccessHandler;
import com.driagon.springboot.datajpa.app.auth.service.JWTService;
import com.driagon.springboot.datajpa.app.models.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*@Autowired
    private DataSource dataSource;*/

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(this.userDetailsService);

        // JDBC
        /*builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id = u.id) where u.username = ?");*/

        //Memoria
       /* PasswordEncoder enconder = this.passwordEncoder;
        User.UserBuilder users = User.builder().passwordEncoder(enconder::encode);

        builder.inMemoryAuthentication()
                .withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
                .withUser(users.username("adrian").password("12345"). roles("USER"));*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**", "/locale").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}