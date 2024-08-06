package com.quantori.auth.service.impl;

import com.quantori.auth.config.AppProperties;
import com.quantori.auth.exception.CustomTokenNotValidException;
import com.quantori.auth.model.entity.UsersEntity;
import com.quantori.auth.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final AppProperties appProperties;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims;
        try {
            claims = extractAllClaims(token);
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
            throw new CustomTokenNotValidException("Token expired!");
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
            throw new CustomTokenNotValidException("Unsupported jwt!");
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
            throw new CustomTokenNotValidException("Malformed jwt!");
        } catch (Exception e) {
            log.error("invalid token", e);
            throw new CustomTokenNotValidException("Invalid token!");
        }

        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(UsersEntity usersEntity) {
        return generateToken(new HashMap<>(), usersEntity);
    }

    @Override
    public void isTokenValid(String token, UsersEntity usersEntity) {
        final String username = extractUsername(token);
        if ((username.equals(usersEntity.getUsername()))) {
            isTokenExpired(token);
        }
    }

    public String generateToken(Map<String, Object> extraClaims, UsersEntity usersEntity) {
        return buildToken(extraClaims, usersEntity, appProperties.getJwtExpiration());
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UsersEntity usersEntity,
            long expiration
    ) {
        extraClaims.put("email", usersEntity.getEmail());

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(usersEntity.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .signWith(getSignInKey())
                .compact();
    }

    private void isTokenExpired(String token) {
        extractExpiration(token);
    }

    private void extractExpiration(String token) {
        extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build().parseSignedClaims(token).getPayload();

    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(appProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
