package com.krugercorp.employeesvaccination.rest;

import com.krugercorp.employeesvaccination.commons.dto.JwtResponse;
import com.krugercorp.employeesvaccination.config.WebSecurityConfig;
import com.krugercorp.employeesvaccination.security.JwtHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The auth controller to handle login requests
 *
 * @author imesha
 */
@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
public class AuthController {

	private Map<String, Object> response = null;
    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtHelper jwtHelper, UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
    public ResponseEntity<?> login(@RequestBody MultiValueMap<String, String> paramMap, @RequestParam("grant_type") String grantType) {	
    	this.response = new HashMap<>();

        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(paramMap.getFirst("client_id"));
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        if (passwordEncoder.matches(paramMap.getFirst("client_secret"), userDetails.getPassword())) {
            Map<String, String> claims = new HashMap<>();
            claims.put("username", paramMap.getFirst("client_id"));

            String authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
            claims.put(WebSecurityConfig.AUTHORITIES_CLAIM_NAME, authorities);
            claims.put("userId", String.valueOf(1));

            JwtResponse jwt = jwtHelper.createJwtForClaims(paramMap.getFirst("client_id"), claims);

            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
    }
}
