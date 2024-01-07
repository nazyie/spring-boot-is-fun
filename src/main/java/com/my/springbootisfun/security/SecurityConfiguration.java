package com.my.springbootisfun.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Authentication (Basic Auth)
 * ==============
 * - Authentication (To identify the user)
 * - Authorization (To process what resource that you eligable to)
 * - Detail of the data flow diagram can be seen on below using the basic security
 * - There are various of way to apply the Spring Security Filters
 *          - thru the application properties
 *          - thru annotation without extending WebSecurityConfigurerAdapter (This depreciated on Spring Security 5.4, Most old project still use it)
 *          - thru annotation with extending WebSecurityConfigurerAdapter
 *
 *  - Here is importance class for this authentication to be work. The rest is the boilerplate to demonstrate the spring security
 *  1. SecurityFilterChain (This to validate the http call)
 *  2. AuthenticationManager for custom authentication
 *  3. Authentication this is to construct the data for the authenticated user (principle, credential)
 *  !! Do not overwhelmn by seeing the class name that exist in this class. It just boilerplate class
 */

/**
 * Authorization
 * =============
 * - There are various of the way for you to authorize the user
 *          - @PreAuthorize("hasRole('USER') and hasAuthority('WRITE_PRIVILEDGES') and hasPermission(#id, 'WRITE')")
 *          - Thru the SecurityFilterChain using the
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/login").permitAll()
//                                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/user").hasAuthority("READ")
                                .anyRequest()
                                .authenticated()
                )
//                .formLogin(Customizer.withDefaults()) - this will generate the default page for login that can be access thru /login
                .httpBasic(Customizer.withDefaults()) // this is requiring the basic auth
                .build();
    }

    /**
     * Authentication
     * ==============
     * - This is to create the in-memory dummy user to login
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .authorities("READ")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .authorities("READ", "WRITE")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    /**
     * Authentication
     * ==============
     * - The AuthenticationManager interface in Spring Security is a core component
     * responsible for authenticating a user. It is part of the authentication process
     * and is used to validate the credentials provided by the user and determine their
     * authenticity
     */
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    /**
     * Configuration
     * =============
     * This determine the algorithm for the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}

/**
 * BASIC AUTHENTICATION (Spring Security)
 * =======================================
 *
 *
 *    +--------+                     +------------------+                     +---------------+
 *    | Client |  HTTP Request with   | Spring Security  |                     | Secured       |
 *    |        |  Authorization Header| Filters          |                     | Resource      |
 *    +--------+                     +------------------+                     +---------------+
 *        |                                      |                                    |
 *        |          Unauthorized (401)           |                                    |
 *        | <------------------------------------>|                                    |
 *        |                                      |                                    |
 *        |     HTTP Request without credentials |                                    |
 *        | <------------------------------------>|                                    |
 *        |                                      |                                    |
 *        |     HTTP Request with credentials    |                                    |
 *        | ------------------------------------>|  Extract credentials from       |
 *        |                                      |  Authorization header and       |
 *        |                                      |  pass to AuthenticationManager  |
 *        |                                      |----------------------------------->|
 *        |                                      |                                    |
 *        |                                      |         Authenticate user         |
 *        |                                      |         using UserDetailsService |
 *        |                                      |                                    |
 *        |                                      |                                    |
 *        |   Authentication Success (200)        |                                    |
 *        | <------------------------------------>|                                    |
 *        |                                      |                                    |
 *        |   Access Secured Resource             |                                    |
 *        | ------------------------------------>|                                    |
 */

