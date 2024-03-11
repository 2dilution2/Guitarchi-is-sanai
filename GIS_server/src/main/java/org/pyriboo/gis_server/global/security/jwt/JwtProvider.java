package org.pyriboo.gis_server.global.security.jwt;

import io.jsonwebtoken.Jwts;

import org.pyriboo.gis_server.domain.users.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

	private final SecretKey secretKey;
	private final Long accessTokenExpirationInMillis = 30 * 60 * 1000L; // 30분
	private final Long refreshTokenExpirationInMillis = 30 * 24 * 60 * 60 * 1000L; // 30일


	public JwtProvider(@Value("${spring.jwt.secret}")String secret) {
		secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
	}

	public String createAccessToken(String email, Role role) {
		return createJwt(email, role, accessTokenExpirationInMillis);
	}

	public String createRefreshToken(String email, Role role) {
		return createJwt(email, role, refreshTokenExpirationInMillis);
	}

	public String getEmail(String token) {

		return Jwts
			.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.get("email", String.class);
	}

	public String getRole(String token) {

		return Jwts
			.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.get("role", String.class);
	}

	public Boolean isExpired(String token) {

		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getExpiration()
			.before(new Date());
	}

	public String createJwt(String username, Role role, Long expiredMs) {

		return Jwts.builder()
			.claim("username", username)
			.claim("role", role)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + expiredMs))
			.signWith(secretKey)
			.compact();
	}
}
