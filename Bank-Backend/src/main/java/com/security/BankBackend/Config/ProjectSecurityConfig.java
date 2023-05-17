package com.security.BankBackend.Config;

import com.security.BankBackend.Filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration  //we tell to spring boot that we have certain configurations that we have defined inside this class
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.securityContext().requireExplicitSave(false)
                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))

                .cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3800L);

                return config;
            }
        }).
                and()/*.csrf().ignoringRequestMatchers("/contact","/register")
                //For Http get Request Spring security doesnot provide any csrf protection
                //only for post and Put Request
                .and()
*/
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)


                .authorizeHttpRequests()
                .requestMatchers("/myAccount","/myBalance","/myLoan","/myCards")
                .authenticated()
                .requestMatchers("/contact","/notices","/register")
                .permitAll()
                        .and().formLogin()
                        .and().httpBasic();

           /*  use anyRequest().denyAll() to deny all requests
                use anyRequest().permitAll() to permit all requests  */

        return http.build();

    }

/*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user= User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .authorities("read")
                .build();
        UserDetails admin= User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }


//    Approch 2 without withDefaultPasswordEncoder and Using NoOpPasswordEncoder
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        InMemoryUserDetailsManager inMemoryUserDetailsManager= new InMemoryUserDetailsManager();
        UserDetails user= User.withUsername("user")
                .password("password")
                .authorities("read")
                .build();
        UserDetails admin= User.withUsername("admin")
                .password("password")
                .authorities("admin")
                .build();
        inMemoryUserDetailsManager.createUser(user);
        inMemoryUserDetailsManager.createUser(admin);
        return inMemoryUserDetailsManager;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
 */

/*

    //Authentication Using JDBC
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){  //DataSource tells that there is a database and it create its object and get the details of the dataBase
        return new JdbcUserDetailsManager(dataSource);
    }

    */


/*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}

