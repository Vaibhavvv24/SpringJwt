package com.example.jwtt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static String SECRET="0a0ce8e6238ff8fcdea75023c3408a929928f37d2c9e9bbedce7e0e87e0aae81";
    public String extractEmail(String jwt) {
        return extractClaim(jwt,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAll(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAll(String jwt){
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(jwt).getBody();
                //sign in key is a secret just like in node js when we put a secret while creation jwt token
    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails ud){
        return Jwts.builder().
                        setClaims(extraClaims).
                        setSubject(ud.getUsername()).       //sets subject as email
                        setIssuedAt(new Date(System.currentTimeMillis())).
                        setExpiration(new Date(System.currentTimeMillis()+1000*60*24)).
                        signWith(getSigninKey(), SignatureAlgorithm.HS256).
                        compact();
    }
    public String generateToken(UserDetails ud){
        return generateToken(new HashMap<>(),ud);
    }
    public Boolean isTokenValid(String token,UserDetails ud){
        String email=extractEmail(token);
        return (email.equals(ud.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiry(token).before(new Date());
    }

    private Date extractExpiry(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Key getSigninKey() {
        byte[] keybytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keybytes);
    }
}
