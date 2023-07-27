package com.giantLink.RH.services.impl;
import com.giantLink.RH.entities.Role;
import com.giantLink.RH.entities.User;
import io.jsonwebtoken.Claims;
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
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {


    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    public String extractUsername(String token){
        return  extractClaim(token,Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);

    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final  String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return  extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Objects> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateTokenWithRole(Role role, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.getRoleName());
        claims.put("userId", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public <T> T extractClaim(String token, Function<Claims,T> ClaimsResolver){

        final Claims claims = extractAllClaims(token);
        return ClaimsResolver.apply(claims);

    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignIngKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    private Key getSignIngKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

