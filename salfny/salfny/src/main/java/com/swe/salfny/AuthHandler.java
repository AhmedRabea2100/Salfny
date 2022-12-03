package com.swe.salfny;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.swe.salfny.user.Credential;
import com.swe.salfny.user.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthHandler {

	@Autowired
	private UserRepository repo;

	public static final long JWT_TOKEN_VALIDITY = 7*24*60*60;

	@Value("${jwt.secret}")
	private String secret;

	public String getEmailFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		Claims claims=null;
		try {
			claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}catch (Exception e){
			throw e;
		}
		return claims;

	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		if(expiration==null) return true;
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public String generateToken(Credential userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getEmail());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	private String email;

	public String getEmail() {
		return email;
	}

	public Boolean validateToken(String token) {

		if (token != null) {
			try {
				email = getEmailFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				return false;
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				return false;
			}catch (Exception e){
				System.out.println(e.getMessage());
				return false;
			}
		} else {
			System.out.println("JWT Token does not begin with Bearer String");
			return false;
		}

		String id= repo.findByEmail(email);

		return (id!=null && !isTokenExpired(token));
	}
}
