package com.hungnguyen.blogweb.Config;


import com.hungnguyen.blogweb.Service.CustomerUserdetailServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    CustomerUserdetailServiece Service;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/index","/dausach","/register","/index/page","/comment","/error",
                                "/theloai","/chude","/chapter","/dausach_tacgia","/register/save"
                                ,"/danhsachcd","/danhsachtl"
                                ,"https://fonts.googleapis.com"
                                ,"https://fonts.gstatic.com"
                                ,"https://fonts.googleapis.com/css2?family=EB+Garamond:wght@400;500&family=Inter:wght@400;500&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap"
                                ,"/assets/css/**","/assets/img/**","/assets/js/**","/assets/scss/**","/assets/vendor/**"
                                ,"/assets1/css/**","/assets1/img/**","/assets1/js/**","/assets1/scss/**","/assets1/vendor/**"
                                ,"/forms/**","/photo/**"
                                ,"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
                                ,"https://fonts.gstatic.com"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/index",true)
                        .failureUrl("/login")
                        .loginProcessingUrl("/login")

                )
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/index")
                        .invalidateHttpSession(true)

                ) ;

        return http.build();
    }

/*
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.withUserDetails(Service).passwordEncoder((Function<String, String>) encoder).build();

        return new InMemoryUserDetailsManager(user);
    }*/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(Service)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
}

