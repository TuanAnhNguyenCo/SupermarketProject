package com.example.demo.config;

import com.example.demo.filter.JwtRequestFilter;
import com.example.demo.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
          httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
          httpSecurity.authorizeRequests().antMatchers("/authenticate","/images/**","/api/v1/home").permitAll();
//        // Phân quyền trên product
          httpSecurity.authorizeRequests().antMatchers(PUT,"/api/v1/product/update/**").hasAnyAuthority("employee");
          httpSecurity.authorizeRequests().antMatchers(POST,"/api/v1/product/insert/**").hasAnyAuthority("employee");
          httpSecurity.authorizeRequests().antMatchers(DELETE,"/api/v1/product/delete/**").hasAnyAuthority("employee");

          //Phân quyền trên order
          httpSecurity.authorizeRequests().antMatchers(PUT,"/api/v1/order/verify/**").hasAnyAuthority("employee");

          // Các request còn lại thì ai cx truy cập đc nhưng phải đăng nhập trước
          httpSecurity.authorizeRequests().anyRequest().authenticated();


        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}
