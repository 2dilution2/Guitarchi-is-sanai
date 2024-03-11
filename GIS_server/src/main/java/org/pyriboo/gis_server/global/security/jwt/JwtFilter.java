package org.pyriboo.gis_server.global.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.pyriboo.gis_server.domain.users.dto.UserDto;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String path = request.getRequestURI();

		if (path.contains("/api/users/signup") || path.contains("/api/users/login")) {
			filterChain.doFilter(request, response);
			return;
		}

		String authorization = request.getHeader("Authorization");
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			System.out.println("token null");
			filterChain.doFilter(request, response);
			return;
		}

		// "Bearer " 접두어 제거
		String token = authorization.substring(7);

		if (jwtTokenProvider.isExpired(token)) {
			System.out.println("token expired");
			filterChain.doFilter(request, response);
			return;
		}

		String username = jwtTokenProvider.getEmail(token);
		String role = jwtTokenProvider.getRole(token);

		UserDto userDTO = new UserDto();
		userDTO.setEmail(username);
		userDTO.setRole(role);

		filterChain.doFilter(request, response);
	}
}