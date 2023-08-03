package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Role;
import com.giantLink.RH.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /**
     * Extract the username from the JWT token.
     *
     * @param token The JWT token from which the username is to be extracted.
     * @return The extracted username.
     */
    public String extractUsername(String token) {

//        try {
        return extractClaim(token, Claims::getSubject);
//        } catch (ExpiredJwtException ex) {
//            throw new ExpiredJwtException(null, null, "Token is expired");
//        }
    }

    /**
     * Extract the role from the JWT token.
     *
     * @param token The JWT token from which the role is to be extracted.
     * @return The extracted role.
     */
    public String extractRoleFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return (String) claims.get("role");
    }

//    /**
//     * Generate a JWT token for the given UserDetails.
//     *
//     * @param userDetails The UserDetails object for which the token is to be generated.
//     * @return The generated JWT token.
//     */
//    public String generateToken(UserDetails) {
//        return buildToken(new HashMap<>(), userDetails, jwtExpiration);
//    }

    /**
     * Check if the provided token is valid for the given UserDetails.
     *
     * @param token       The JWT token to be validated.
     * @param userDetails The UserDetails object to validate the token against.
     * @return True if the token is valid, otherwise false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Check if the provided token has expired.
     *
     * @param token The JWT token to be checked.
     * @return True if the token has expired, otherwise false.
     */
    private boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    /**
     * Extract the expiration date from the JWT token.
     *
     * @param token The JWT token from which the expiration date is to be extracted.
     * @return The extracted expiration date.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generate a JWT token with the given extra claims for the UserDetails.
     *
     * @param extraClaims Extra claims to be included in the JWT token.
     * @param userDetails The UserDetails object for which the token is to be generated.
     * @param expiration  The expiration time for the token in milliseconds.
     * @return The generated JWT token.
     */
    public String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generate a JWT token with the specified role and user information.
     *
     * @param role The Role object for the user.
     * @param user The User object for which the token is to be generated.
     * @return The generated JWT token.
     */
    public String generateTokenWithRole(Role role, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.getRoleName());
        claims.put("userId", user.getId());
        return buildToken(claims, user, jwtExpiration);
    }

    /**
     * Generate a refresh token with the specified role and user information.
     *
     * @param role The Role object for the user.
     * @param user The User object for which the token is to be generated.
     * @return The generated refresh token.
     */
    public String generateRefreshTokenWithRole(Role role, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.getRoleName());
        claims.put("userId", user.getId());
        return buildToken(claims, user, refreshExpiration);
    }

    /**
     * Extract a specific claim from the JWT token using the provided ClaimsResolver function.
     *
     * @param token          The JWT token from which the claim is to be extracted.
     * @param claimsResolver The function to resolve the claim from the Claims object.
     * @param <T>            The type of the extracted claim.
     * @return The extracted claim value.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims from the JWT token.
     *
     * @param token The JWT token from which all claims are to be extracted.
     * @return The Claims object containing all the claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Get the signing key used to sign the JWT tokens.
     *
     * @return The signing key as a Key object.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
