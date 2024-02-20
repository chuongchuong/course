package courseonline4399.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


            http.csrf((csrf) -> csrf.ignoringRequestMatchers("/login","/register","/registerTeacher","/registration","/rest/orders/**","/rest/courses/**","/rest/support/**","/admin/support/**" ,"/admin/voucher/**" ,"/cart/checkVourcher/**")
                            .ignoringRequestMatchers("/api/**","/admin/**","/forgotpass","/resetpass","/rest/files/**","/registerStudentAccount","/registerTeacherAccount","/profile/changepass","/src/**","/images/**",
                                    "/rest/quiz/**","/rest/studyresult/**","/admin/voucher/**"))

                .authorizeHttpRequests((requests) -> requests.requestMatchers("/home" , "/" , "").permitAll()

                        .requestMatchers(
                                "/assets/**", "/css/**", "/library/**",
                                "/icons/**", "/images/**", "/js/**", "/vendor/**", "/script/**", "/script/**","/src/**"
                        ).permitAll()
                        .requestMatchers("/registerAccount").permitAll()
                        .requestMatchers("/coursedetail").permitAll()
                        .requestMatchers("/register").permitAll()
                                .requestMatchers("/registerTeacherAccount").permitAll()
                                .requestMatchers("/registerStudentAccount").permitAll()
                                .requestMatchers("/registerTeacher").permitAll()
                        .requestMatchers("/home", "/", "").permitAll()
                        .requestMatchers("/courses/**").permitAll() // test
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/profile", "/profile/edit").authenticated()
                        .requestMatchers("/library/**").authenticated()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/admintemplate/**").permitAll()
                        .requestMatchers("/oauth2/**").permitAll()
                        .requestMatchers("/sorted-by-price-asc").permitAll()
                        .requestMatchers("/find-by-price").permitAll()
                        .requestMatchers("/sorted-by-price-desc").permitAll()
                        .requestMatchers("/admin/**").permitAll()
                        .requestMatchers("/search_courses/**").permitAll() // test
                        .requestMatchers("/rest/**").permitAll() // test
                        .requestMatchers("/detail_course/**").permitAll() // test
                        .requestMatchers("/cart/**").authenticated() // test
                        .requestMatchers("/checkout/**").permitAll() // test
                        .requestMatchers("/api/**").permitAll() // test
                        .requestMatchers("/order/**").authenticated() // test
                        .requestMatchers("/api_payment/**").permitAll() // test
                        .requestMatchers("/payment/**").permitAll() // test
                        .requestMatchers("/rest/orders").permitAll() // test
                        .requestMatchers("/form").permitAll() // test
                        .requestMatchers("/blog").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/category/**").permitAll()
                        .requestMatchers("/error/404").permitAll()
                        .requestMatchers("/forgotpass").permitAll()
                        .requestMatchers("/forgotpassword").permitAll()
                        .requestMatchers("/resetpass").permitAll()
                        .requestMatchers("/resetpassword").permitAll()
                                .requestMatchers("/rest/support/**").permitAll()
                                .requestMatchers("/admin/support/**").authenticated()
                                .requestMatchers("/profile/changepass").authenticated()
                                .requestMatchers("/rest/quiz/**").hasAnyRole("ADMIN")
                                .requestMatchers("/rest/studyresult/**").permitAll()
                                .requestMatchers("/quizz").permitAll()
                                .requestMatchers("/quizz/**").permitAll()
                                .requestMatchers("/admin/voucher/**").permitAll()
                                .requestMatchers("/cart/checkVourcher/**").permitAll()

//                        .requestMatchers("/rest/files/**").permitAll()// test




                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException)
                                -> response.sendRedirect("/error/404")))
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll());

        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/oauth2/login/success", true)
                .failureUrl("/auth/login/error")
                .authorizationEndpoint(authorization -> authorization
                        .baseUri("/oauth2/authorization")
                )
        );

        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    }


