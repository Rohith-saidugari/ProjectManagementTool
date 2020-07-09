package com.rohith.ppmtool.config.security;

import com.rohith.ppmtool.model.User;
import static com.rohith.ppmtool.config.security.SecurityConstants.EXPIRATION_TIME;
import static com.rohith.ppmtool.config.security.SecurityConstants.SECRET;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for Token Generation Validation and Get User Id From token claims
 */
@Component
public class JWTTokenProvider {

    public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);
        String userId = Long.toString(user.getId());
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",userId);
        claims.put("username",user.getUsername());
        claims.put("firstName",user.getFirstName());
        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();

    }
}
