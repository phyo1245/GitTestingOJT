package com.c_s_boot.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    // Secret key encoded in Base64
    private String jwtSecret = "7vWcVtAvT5OhnKAHADRb/h1gvvtz7uViMPdQKN1Oa1r5WT+MQ4lJQpvl+hN7cQ4iAzANs7cT0XbcFukNisX0nw==";

    // JWT expiration time in milliseconds (e.g., 24 hours)
    private int jwtExpirationMs = 60 * 1000;

    // Decode the Base64 encoded secret key and create a Key object
    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    // Generate the JWT token using the user's email
    public String generateJwtToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())  // Use email as the subject
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))  // Set expiration
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)  // Use the signing key
                .compact();  // Create the token
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
            return !isTokenExpired(authToken);  // Check if the token has expired
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        }

        return false;  // Return false if token is expired or invalid
    }


    // Extract email (subject) from the token
    public String getEmailFromToken(String authToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Use the signing key
                .build()
                .parseClaimsJws(authToken)
                .getBody()
                .getSubject();  // The subject in the token is the email
    }

    // Check if the token has expired
    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Use the signing key
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());  // Check if the expiration date has passed
    }
}
