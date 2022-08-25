package com.xzjie.cms.security.token;

import cn.hutool.core.codec.Base64;
import com.xzjie.cms.configure.SecurityProperties;
import com.xzjie.cms.security.SecurityUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
//@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityTokenProvider {

    public static final long jwt_token_validity = 5 * 60 * 60;
    @Autowired
    private SecurityProperties properties;
    @Autowired
    private KeyPair keyPair;

    @Bean
    public KeyPair getKeyPair() {
        ClassPathResource resource = new ClassPathResource(properties.getKeyStore());
        char[] keyStorePassword = properties.getKeyStorePassword().toCharArray();
        char[] keyPassword = properties.getKeyPassword().toCharArray();
        String keyAlias = properties.getKeyAlias();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, keyStorePassword);
        return keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword);
    }


    public String generateToken(UserDetails userDetails) {
        Date expiration = new Date(System.currentTimeMillis() + properties.getExpired() * 1000);
        Map<String, Object> claims = new HashMap<>();
        JwtBuilder builder = Jwts.builder()
                .addClaims(claims)
                .setSubject(userDetails.getUsername())
                .signWith(this.getKey(), properties.getAlgorithm())
                .setIssuedAt(new Date())
                .setExpiration(expiration);

        return builder.compact();
    }

    public String generateToken(Authentication authentication) {

        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();

        return generateToken(userDetails);
    }

    /**
     * retrieve username from jwt token
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * for retrieveing any information from token we will need the secret key
     */

    private Claims getClaimsFromToken(String token) {
        JwtParser parser = this.getJwtParser();
        return parser.parseClaimsJws(token).getBody();
    }


    /**
     * retrieve expiration date from jwt token
     */
    public Date getExpirationFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    /**
     * check if the token has expired
     */
    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails account) {
        final String username = getUsernameFromToken(token);
        return (username.equals(account.getUsername())) && !isTokenExpired(token);
    }

    private JwtParser getJwtParser() {
        return Jwts.parserBuilder().setSigningKey(this.getKey()).build();
    }

    private Key getKey() {
        switch (properties.getAlgorithm()) {
            case RS256:
            case RS512:
                return keyPair.getPrivate();
            case HS256:
            case HS512:
                return Keys.hmacShaKeyFor(Base64.decode(properties.getSecret()));
            default:
                throw new SecurityException("key 密钥错误.");
        }
    }
}
