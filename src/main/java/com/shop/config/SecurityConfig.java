package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
        ;
        http.authorizeRequests()
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        return http.build();
//        http.formLogin()
//                .loginPage("/members/login")
//                .defaultSuccessUrl("/")
//                .usernameParameter("email")
//                .failureUrl("/members/login/error")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members"))
//                .logoutSuccessUrl("/");
//        http.authorizeRequests()
//                .mvcMatchers("/", "/members/**", "/item/**", "images/**").permitAll()
//                .mvcMatchers("admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated();
////        http.exceptionHandling()
////                .authenticationEntryPoint();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        StandardPasswordEncoder() -SHA256
//        NoOpPasswordEncoder() -암호화 하지 않은 데이터
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.setUsersByUsernameQuery("select email as userName, password, '1' from member" +
//                " where email=?");
//        users.setAuthoritiesByUsernameQuery("select email as userName, role as authority");
//    }
}
