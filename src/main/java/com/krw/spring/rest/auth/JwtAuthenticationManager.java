package com.krw.spring.rest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.krw.spring.constants.AuthConstants;
import com.krw.spring.service.UserService;
import com.krw.spring.service.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// BearerTokenExtractor sets token on principal.
		// See OAuth2AuthenticationProcessingFilter.
		if (StringUtils.isEmpty(authentication.getPrincipal())) {
			throw new BadCredentialsException("Bad token");
		}
		String token = authentication.getPrincipal().toString();

		try {
			final Claims claims = Jwts.parser().setSigningKey(AuthConstants.getJwtPrivateKey()).parseClaimsJws(token)
					.getBody();

			User user = (User) userService.loadUserByUsername(claims.getAudience());
			
			if (user == null) {
				throw new UsernameNotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
			}

			return new UsernamePasswordAuthenticationToken(user.getUuid(), user.getPassword(), user.getAuthorities());
		} catch (final SignatureException e) {
			throw new BadCredentialsException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}
	}

}
