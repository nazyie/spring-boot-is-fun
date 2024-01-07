# Spring Security (Authentication & Authorization)
<hr />

#### Basic Functionalities Out-Of-Box

1. `SecurityFilterChain` bean to filter all the incoming request
   2. ```
      http
        .authorizeHttpRequests((authorize) -> authorize
          .requestMatchers("/login/**").permitAll()
          .requestMatchers("/admin/**").hasRole("ADMIN").hasAuthority("READ_PRIVILEDGE")
          .anyRequest().authenticated()
       ).httpBasic()
          .build()
      ```
2. `AuthenticationManager` bean required to manage the authentication process
3. `PasswordEncoder` bean required to manage the password encoder. Eg. `BCryptPasswordEncoder`
4. `UserDetails` interface class is contain information like USERNAME, PASSWORD
5. `UserDetailService` is use to load user-specific data used by `UserDetails`

#### Basic Functionalities for Authorization
1. Either you apply in the `SecurityFilterChain`
2. Apply the `@PreAuthorize(hasAuthority'READ_ACCESS') and hasRole('ADMIN')` by `@EnableMethodSecurity`