package net.mohamadi.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private Long jwtExpiration;

    private Key key;

    @PostConstruct//یعنی بعد از ساخته شدن Bean و تزریق همه وابستگی‌ها، یک بار این متد را اجرا کن
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    }

    public String generateToken(String userName) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .subject(userName)
                .issuedAt(now)
                .expiration(expiredDate)
                .signWith(key)
                .compact();
    }

    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;

        } catch (JwtException | IllegalArgumentException e) {

            System.out.println("Invalid JWT Token : " + token + " => " + e.getMessage());

            return false;

        }
    }

}
