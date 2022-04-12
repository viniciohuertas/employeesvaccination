package com.krugercorp.employeesvaccination.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.krugercorp.employeesvaccination.service.impl.JpaUserDetailsService;

@Component
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHORITIES_CLAIM_NAME = "roles";
    
    @Autowired
	private JpaUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(configurer ->
                        configurer
                                .antMatchers(
                                        "/error",
                                        "/login",
                                        "/", "/css/**", "/js/**", "/images/**","/listar**",
                        				"/v3/api-docs",
                        				"/configuration/ui",
                        				"/swagger-resources/**",
                        				"/configuration/security",
                        				"/swagger-ui.html",
                        				"/swagger-ui/**",
                        				"/javainuse-openapi/**",
                        				"/v3/api-docs/swagger-config",
                        				"/webjars/**"
                                )
                                .permitAll()
				/*
				 * .anyRequest() .authenticated()
				 */
                );

        // JWT Validation Configuration
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(authenticationConverter());
    }
    
    

	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() {
	 * System.out.println("Carga usuarios"); InMemoryUserDetailsManager manager =
	 * new InMemoryUserDetailsManager();
	 * 
	 * UserDetails user1 = User .withUsername("user1") .authorities("ADMIN")
	 * .passwordEncoder(passwordEncoder::encode) .password("1234") .build();
	 * manager.createUser(user1);
	 * 
	 * UserDetails user2 = User .withUsername("user2") .authorities("EMPLOYEE")
	 * .passwordEncoder(passwordEncoder::encode) .password("1234") .build();
	 * manager.createUser(user2);
	 * 
	 * UserDetails user3 = User .withUsername("user3") .authorities("EMPLOYEE")
	 * .passwordEncoder(passwordEncoder::encode) .password("1234") .build();
	 * manager.createUser(user3);
	 * 
	 * UserDetails user4 = User .withUsername("user4") .authorities("EMPLOYEE")
	 * .passwordEncoder(passwordEncoder::encode) .password("1234") .build();
	 * manager.createUser(user4);
	 * 
	 * return manager; }
	 */

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}

	protected JwtAuthenticationConverter authenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("");
        authoritiesConverter.setAuthoritiesClaimName(AUTHORITIES_CLAIM_NAME);

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }
}
