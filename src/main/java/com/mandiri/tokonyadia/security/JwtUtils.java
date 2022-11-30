package com.mandiri.tokonyadia.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private  final Logger log= LoggerFactory.getLogger(JwtUtils.class);

    @Value("${tokonyadia.jwt-secret}")
    private String jwtSecret;

    @Value("${tokonyadia.jwt-expiration}")
    private Integer jwtExpiration;

    public String generateTokenFromUsername(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .compact();
    }
    public String getUsernameJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody()
                .getSubject();
    }
    public boolean validationJwtToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                    return true;
        }catch(MalformedJwtException e){
            log.error("Invalid JWT Token:{}",e.getMessage());
        } catch (ExpiredJwtException e){
            log.error("JWT Token is expired:{}", e.getMessage());
        }catch(UnsupportedJwtException e){
            log.error("JWT Token is unsupported :{}",e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("JWT Token String is Empty:{}",e.getMessage());
        }
        return false;
    }
}
