package com.krugercorp.employeesvaccination.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.krugercorp.employeesvaccination.commons.dto.JwtResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {
	
	private final RSAPrivateKey privateKey;
	private final RSAPublicKey publicKey;
	
	public JwtHelper(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	
	public JwtResponse createJwtForClaims(String subject, Map<String, String> claims) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Instant.now().toEpochMilli());
		calendar.add(Calendar.DATE, 1);
		
		JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);
		
		// Add claims
		claims.forEach(jwtBuilder::withClaim);
		
		// Add expiredAt and etc
		String accessToken = jwtBuilder
				.withNotBefore(new Date())
				.withExpiresAt(calendar.getTime())
				.sign(Algorithm.RSA256(publicKey, privateKey));
		
		JwtResponse jwt = JwtResponse.builder()
				.tokenType("bearer")
				.accessToken(accessToken)
				.issuedAt(new Date() +  "")
				.clientId(subject)
				.expiresIn(3600)
				.build();
		return jwt;
	}
}
