package com.krw.spring.rest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.krw.spring.constants.AuthConstants;
import com.krw.spring.service.UserService;
import com.krw.spring.service.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		/**
		 * Throwing exceptions are not AuthenticationException.
		 * OAuth2AuthenticationProcessingFilter doesn't catch the AuthenticationException.
		 * It only catch OAuth2Exception. So this manager also should throws OAuth2Exception too.
		 * See OAuth2AuthenticationProcessingFilter.doFilter
		 */
		
		// BearerTokenExtractor sets token on principal.
		// See OAuth2AuthenticationProcessingFilter.
		if (StringUtils.isEmpty(authentication.getPrincipal())) {
			throw new UnauthorizedUserException("Bad token");
		}
		String token = authentication.getPrincipal().toString();

		try {
			final Claims claims = Jwts.parser().setSigningKey(AuthConstants.getJwtPrivateKey()).parseClaimsJws(token)
					.getBody();

			User user = (User) userService.loadUserByUsername(claims.getAudience());
			
			if (user == null) {
				throw new UnauthorizedUserException(HttpStatus.NOT_FOUND.getReasonPhrase());
			}

			return new UsernamePasswordAuthenticationToken(user.getUuid(), user.getPassword(), user.getAuthorities());
		} catch (final SignatureException e) {
			throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		} catch (ExpiredJwtException e) {
			throw new UserDeniedAuthorizationException("Token expired.");
		}
	}

}
