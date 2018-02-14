package com.secure.with.jwt.securewithjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private final String secret = "**********secret*********";
    public JwtUser validate(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            JwtUser jwtUser = new JwtUser();
            jwtUser.setUserName(claims.getSubject());
            jwtUser.setRole((String) claims.get("role"));
            jwtUser.setId(((Integer)claims.get("id")).longValue());
            return jwtUser;
        } catch (Exception e) {
            System.out.println("exception:"+e);
            return null;
        }

    }

    public String getToken(JwtUser jwtUser) {
        Claims claims = Jwts.claims();
        claims.setSubject(jwtUser.getUserName());
        claims.put("id", jwtUser.getId());
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret.getBytes()).compact();
    }
}
