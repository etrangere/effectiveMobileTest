package com.em.test_em.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(createRequestMatcher("/v3/**")).permitAll()
                    .requestMatchers(createRequestMatcher("/swagger-ui/**")).permitAll()
             
            
                    // .requestMatchers(createRequestMatcher("/api/v1/**")).authenticated()
                    //.requestMatchers(createRequestMatcher("/api/v1/user")).permitAll()
                    //.requestMatchers(createRequestMatcher("/api/v1/comment")).authenticated()
               
                    /*  .requestMatchers(createRequestMatcher("/swagger-ui/")).permitAll()
                    .requestMatchers(createRequestMatcher("/v3/api-docs")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/update_task/{task_id}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{user_id}/update_user")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/{task_id}/removeExecutor/{userExecutor_id}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/{task_id}/addExecutor/{userExecutor_id}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/create_task")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/update_task_status/{task_id}/{status_code}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/update_task_priority/{task_id}/{priority_code}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/create_user")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/{task_id}/getAll_task_comment_holder_id_task_id")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/getAll_user_task_by_status/{status}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/getAll_user_task_by_priority/{priority}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/getAll_tasks_comments_holder")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskExecutor_id}/getAll_tasks_comments_executor")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{user_id}/delete_user")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/user/{userTaskHolder_id}/delete_task/{task_id}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/comment/{task_id}/getById_comment/{comment_id}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/comment/{task_id}/create_comment")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/comment/{task_id}/getAll_comments")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/comment/{task_id}/delete_comment/{comment_id}")).permitAll()
                    .requestMatchers(createRequestMatcher("/api/v1/comment/{task_id}/update_comment/{comment_id}")).permitAll()
                   */
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout((logout) -> logout.permitAll())
         
            //   .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/api/**")))  // Ignore CSRF for your API endpoints
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private RequestMatcher createRequestMatcher(String pattern) {
        return new AntPathRequestMatcher(pattern);
    }
}
