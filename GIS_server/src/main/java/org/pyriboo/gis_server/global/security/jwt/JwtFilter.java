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

		String authorization = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {

			if(cookie.getName().equals("Authorization")) {

				authorization = cookie.getValue();
			}
		}

		if (authorization == null) {

			System.out.println("token null");
			filterChain.doFilter(request, response);

			return;
		}

		String token = authorization;

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